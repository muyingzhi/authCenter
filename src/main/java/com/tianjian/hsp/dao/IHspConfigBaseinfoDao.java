package com.tianjian.hsp.dao;

import com.tianjian.hsp.bean.CommConfigBean;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspDeptBaseinfo;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Component("hspConfigBaseinfoDao")
public interface IHspConfigBaseinfoDao {

	List<HashMap<String,Object>> getHspInformationList(String staffHospitalCode);

	List<HashMap<String, Object>> getNowHspDeptList(String staffHospitalId);

	
	
	
	List<HspConfigBaseinfo> getHspConfigBaseinfo(String id);
	/**
	 * 通过参数查询对应的名称
	 * @param tableName(表名)
	 * @param resultName(返回的字段名)
	 * @param whereId(查询的条件字段)
	 * @param strName(传入的参数值)
	 * @return
	 */
	String getNameById(String tableName, String resultName, String whereId,
			String strName);
	/**
	 * 查询字典中
	 * @param tableName(表名)
	 * @param seqNo(返回的第一个字段)
	 * @param itemName(返回的第二个字段)
	 * @return
	 */
	@Cacheable(value="dicts",key="'findByBean'+#tableName+'_'+#seqNo+'_'+#itemName")
	List<CommConfigBean> findByBean(String tableName, String seqNo, String itemName);
	/**
	 * 查询地区字典
	 * @param tableName(表名)
	 * @param itemCode(代码字段)
	 * @param itemName(名称字段)
	 * @param parentId(父级代码字段)
	 * @param strValue(父级赋值)
	 * @param strValue1(级别赋值)
	 * @return
	 */
	@Cacheable(value="org",key="'getByParent'+#tableName+'_'+#itemCode+'_'+#itemName+'_'+#parentId+'_'+#strValue+'_'+#strValue1")
	List<?> getByParent(String tableName, String itemCode, String itemName,String parentId, String strValue, String strValue1);
	/**
	 * 查询机构表的最大seqNo
	 * @return
	 */
	Long seqNoMaker();

	String getP2ValueByP1(String blank);
	/**
	 * 修改机构信息
	 * @param data
	 */
	void update(HspConfigBaseinfo data);
	/**
	 * 添加机构信息
	 * @param data
	 */
	void add(HspConfigBaseinfo data);

	void add1(String string, String string2);
	
	void delete(String string, String string2, String hspId, String string3,
			String string4);
	
	List<HspDeptBaseinfo> getHspDeptBaseinfo(String blank, String blank2);
	/**
	 * 更新科室信息
	 * @param hdb
	 */
	void updateDept(HspDeptBaseinfo hdb);
	/**
	 * 查询科室最大序号
	 * @param string
	 * @param string2
	 * @return
	 */
	String getMaxSeqNo(String string, String string2);
	/**
	 * 添加科室
	 * @param hdb
	 */
	void addDept(HspDeptBaseinfo hdb);


	List<HashMap<String,Object>> getHospital();
	HspConfigBaseinfo getById(String id);



	

}
