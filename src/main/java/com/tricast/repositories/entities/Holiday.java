package com.tricast.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Holidays")
public class Holiday implements Serializable  {

	private static final long serialVersionUID = 149087048970724608L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "accountid")
    private Account account;

    @Column(name = "fromday")
    private String fromDay;
    
    @Column(name = "today")
    private String toDay;
    
    @Column(name = "type")
    private Long type;
    
    @Column(name = "actualdaycount")
    private Integer actualDayCount;
}
