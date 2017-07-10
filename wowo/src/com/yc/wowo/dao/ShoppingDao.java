package com.yc.wowo.dao;

import java.util.List;

import com.yc.wowo.entities.Shopping;

public interface ShoppingDao {
	public Integer add(String sname,String prov,String city,String area,String points,String tel,String date,String info );
	
	public List<Shopping> find(Integer pageNo,Integer pageSize);
	
	public int update(String sname,String prov,String city,String area,String points,String tel,String date,String info,String spid);
	
	public Integer del(Integer spid);

}
