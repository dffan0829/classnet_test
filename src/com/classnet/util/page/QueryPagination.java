package com.classnet.util.page;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class QueryPagination extends AbstractPageination {

	private String query;
	private Map<String, Object> param;
	private String totalQuery;
	private Map<String, Object> totalParam;
	//private HibernateTemplate ht;

	
	public QueryPagination(String query,
			Map<String, Object> param, String totalQuery,
			Map<String, Object> totalParam,int page, int page_size) {
		super(page, page_size);
		this.query = query;
		this.param = param;
		this.totalQuery = totalQuery;
		this.totalParam = totalParam;
	}

	public QueryPagination(String query, Map<String, Object> param, int page,
			int page_size) {
		super(page,page_size);
		this.query = query;
		this.param = param;
		this.totalQuery=query;
		this.totalParam=param;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yili.tuangou.util.page.IPagination#getPage()
	 */
	@SuppressWarnings("unchecked")
	public List getPage() {
		return (List) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				int first = (page - 1) * page_size;
				Query q = session.createQuery(query).setMaxResults(page_size);
				if (param != null && !param.isEmpty()) {
					for (Iterator<Map.Entry<String, Object>> it = param
							.entrySet().iterator(); it.hasNext();) {
						Map.Entry<String, Object> entry = it.next();
						q.setParameter(entry.getKey(), entry.getValue());
					}
				}
				return q.setFirstResult(first > 0 ? first : 0).list();
			}

		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yili.tuangou.util.page.IPagination#size()
	 */
	public int size() {
		try {
			return (Integer) ht.execute(new HibernateCallback() {

				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					// TODO Auto-generated method stub
					Query q = session.createQuery(totalQuery);
					if (totalQuery != null && !totalQuery.equals("")) {
						for (Iterator<Map.Entry<String, Object>> it = totalParam
								.entrySet().iterator(); it.hasNext();) {
							Map.Entry<String, Object> entry = it.next();
							q.setParameter(entry.getKey(), entry.getValue());
						}
					}
					return NumberUtils.toInt(q.setMaxResults(1).uniqueResult().toString());
				}

			});
		} catch (Exception e) {
			return 0;
		}
	}


}
