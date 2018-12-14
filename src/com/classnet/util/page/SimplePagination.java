package com.classnet.util.page;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;

public class SimplePagination extends AbstractPageination {

	private DetachedCriteria dc;
	private Order order;
	@SuppressWarnings("unchecked")
	private List list;
	private int size_count=0;
	private boolean rootEntity;
	
	public SimplePagination(DetachedCriteria dc,Order order, int page, int page_size) {
		this(dc,order,false,page,page_size);
	}
	public SimplePagination(DetachedCriteria dc,Order order, boolean rootEntity,int page, int page_size) {
		super(page,page_size);
		this.dc = dc;
		this.order=order;
		this.page = page;
		this.page_size = page_size;
		this.rootEntity=rootEntity;
		init();
//		System.out.println(page+"     "+page_size);
	} 
	
	public void init(){
		ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				Criteria criteria=dc.getExecutableCriteria(session);
				size_count=(Integer)criteria.setProjection(Projections.rowCount()).uniqueResult();
								
				criteria.setProjection(null);
				int first = (page - 1) * page_size;
				if(rootEntity)
					dc.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
				criteria.addOrder(order);
				list= criteria.setMaxResults(
						page_size).setFirstResult(first > 0 ? first : 0).list();				
				return null;
			}


		});
	}

	@SuppressWarnings("unchecked")
	public List getPage() {		
		return list;
	}

	public int size() {		
		return size_count;
	}
}
