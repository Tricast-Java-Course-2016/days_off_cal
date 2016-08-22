package com.tricast.web.dao;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class JdbcTransactionInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		WorkspaceImpl workspace = null;
		try {
			workspace = (WorkspaceImpl) methodInvocation.getArguments()[0];
		} catch (ClassCastException ex) {
			throw new ClassCastException(methodInvocation.getMethod().getName()
					+ " does not have Workspace as it's first argument in the signature!");
		}

		try {
			workspace.startSession();
			Object returnValue = methodInvocation.proceed();
			workspace.commit();
			return returnValue;
		} catch (Throwable thr) {
			workspace.rollback();
			throw thr;
		}
	}

}
