package com.classnet.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface IHibernateSupportDao<T> {

	public abstract T save_(T entity);

	public abstract void update_(T entity);

	public abstract void delete_(T entity);

	@SuppressWarnings("unchecked")
	public abstract T selectById(Class<T> clz, Serializable id);

	@SuppressWarnings("unchecked")
	public abstract List findByExample(DetachedCriteria dc);

	@SuppressWarnings("unchecked")
	public abstract List findByExample(DetachedCriteria dc, int rows);
	@SuppressWarnings("unchecked")
	public List findByExample(DetachedCriteria dc, int start, int rows);

	@SuppressWarnings("unchecked")
	public abstract T uniqueResult(DetachedCriteria dc);

	public abstract int rows(DetachedCriteria dc);
	
	@SuppressWarnings("unchecked")
	public abstract List findByExample(String sql);

}