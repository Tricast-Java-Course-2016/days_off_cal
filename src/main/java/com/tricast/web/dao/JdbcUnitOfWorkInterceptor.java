package com.tricast.web.dao;

import java.sql.Connection;

import javax.sql.DataSource;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.tricast.web.annotations.JdbcUnitOfWork;

// See http://groups.google.com/group/google-guice/browse_thread/thread/097ed64607dcbd90
/**
 * Ad hoc JDBC support for Guice.
 * 
 * @author Alen Vrecko
 */
public class JdbcUnitOfWorkInterceptor implements MethodInterceptor,
		Provider<Connection> {

	// can't use C'tor injection with interceptors using field instead
	@Inject
	public DataSource ds;

	private ThreadLocal<Connection> threadLocalConn = new ThreadLocal<Connection>();

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Connection conn = threadLocalConn.get();

		// the thread local connection can only be set by the interceptor
		// if it is not null the interceptor was already invoked
		if (conn != null) {
			// just continue
			return methodInvocation.proceed();
		}

		// we must open a new connection and appropriately close it at the end
		// if we fail to get the connection, no problem, the target method will
		// not get invoked at all
		conn = ds.getConnection();

		// force auto commit to false
		conn.setAutoCommit(false);

		// set Thread Local connection
		threadLocalConn.set(conn);

		// make sure we commit/rollback, close the connection and unset the
		// thread local around top-level method call
		try {
			Object returnValue = methodInvocation.proceed();
			JdbcUnitOfWork annotation = methodInvocation.getMethod()
					.getAnnotation(JdbcUnitOfWork.class);
			boolean toCommit = annotation.commit();
			if (toCommit) {
				conn.commit();
			}
			return returnValue;
		} catch (Throwable thr) {
			conn.rollback();
			throw thr;
		} finally {
			threadLocalConn.set(null);
			conn.close();
		}
	}

	public Connection get() throws OutOfTransactionException {
		Connection conn = threadLocalConn.get();
		// if the interceptor would fail to get the connection
		// we would not get to call the provider from client code at all
		if (conn == null) {
			throw new OutOfTransactionException();
		}
		return conn;
	}

}