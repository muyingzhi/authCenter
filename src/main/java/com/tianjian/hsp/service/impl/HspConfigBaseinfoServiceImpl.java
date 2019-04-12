package com.tianjian.hsp.service.impl;

import com.tianjian.hsp.bean.*;
import com.tianjian.hsp.dao.IHspConfigBaseinfoDao;
import com.tianjian.hsp.service.IHspConfigBaseinfoService;
import com.tianjian.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("hspConfigBaseinfoService")
public class HspConfigBaseinfoServiceImpl implements IHspConfigBaseinfoService {
	
	@Resource
	private IHspConfigBaseinfoDao hspConfigBaseinfoDao;
	
	// -------------------------------------------------------------------------------------
	/** 字典库的处理 */
	private String[] commConfigUnittypeId;
	private String[] commConfigUnittypeName;
	private String[] commConfigUnitgradeId;
	private String[] commConfigUnitgradeName;
	private String[] commConfigEconkindId;
	private String[] commConfigEconkindName;
	private String[] commConfigLocationId1;
	private String[] commConfigLocationName1;
	private String[] commConfigLocationId2;
	private String[] commConfigLocationName2;
	private String[] commConfigLocationId3;
	private String[] commConfigLocationName3;
	private String[] parentItemCodess;
	private String[] parentItemNamess;
	private String[] commConfigLocationTownId;
	private String[] commConfigLocationTownIdname;
	private String[] commClvId;
	private String[] commClvIdname;
	private String[] CommConfigFtManageIds; // 机构分类管理-修改后
	private String[] CommConfigFtManageId_names;
	
	
	public String[] getCommConfigFtManageIds() {
		return CommConfigFtManageIds;
	}

	public void setCommConfigFtManageIds(String[] commConfigFtManageIds) {
		CommConfigFtManageIds = commConfigFtManageIds;
	}

	public String[] getCommConfigFtManageId_names() {
		return CommConfigFtManageId_names;
	}

	public void setCommConfigFtManageId_names(String[] commConfigFtManageId_names) {
		CommConfigFtManageId_names = commConfigFtManageId_names;
	}

	public String[] getCommConfigUnittypeId() {
			return commConfigUnittypeId;
		}

		public void setCommConfigUnittypeId(String[] commConfigUnittypeId) {
			this.commConfigUnittypeId = commConfigUnittypeId;
		}

		public String[] getCommConfigUnittypeName() {
			return commConfigUnittypeName;
		}

		public void setCommConfigUnittypeName(String[] commConfigUnittypeName) {
			this.commConfigUnittypeName = commConfigUnittypeName;
		}

		public String[] getCommConfigUnitgradeId() {
			return commConfigUnitgradeId;
		}

		public void setCommConfigUnitgradeId(String[] commConfigUnitgradeId) {
			this.commConfigUnitgradeId = commConfigUnitgradeId;
		}

		public String[] getCommConfigUnitgradeName() {
			return commConfigUnitgradeName;
		}

		public void setCommConfigUnitgradeName(String[] commConfigUnitgradeName) {
			this.commConfigUnitgradeName = commConfigUnitgradeName;
		}

		public String[] getCommConfigEconkindId() {
			return commConfigEconkindId;
		}

		public void setCommConfigEconkindId(String[] commConfigEconkindId) {
			this.commConfigEconkindId = commConfigEconkindId;
		}

		public String[] getCommConfigEconkindName() {
			return commConfigEconkindName;
		}

		public void setCommConfigEconkindName(String[] commConfigEconkindName) {
			this.commConfigEconkindName = commConfigEconkindName;
		}

		public String[] getCommConfigLocationId1() {
			return commConfigLocationId1;
		}

		public void setCommConfigLocationId1(String[] commConfigLocationId1) {
			this.commConfigLocationId1 = commConfigLocationId1;
		}

		public String[] getCommConfigLocationName1() {
			return commConfigLocationName1;
		}

		public void setCommConfigLocationName1(String[] commConfigLocationName1) {
			this.commConfigLocationName1 = commConfigLocationName1;
		}

		public String[] getCommConfigLocationId2() {
			return commConfigLocationId2;
		}

		public void setCommConfigLocationId2(String[] commConfigLocationId2) {
			this.commConfigLocationId2 = commConfigLocationId2;
		}

		public String[] getCommConfigLocationName2() {
			return commConfigLocationName2;
		}

		public void setCommConfigLocationName2(String[] commConfigLocationName2) {
			this.commConfigLocationName2 = commConfigLocationName2;
		}

		public String[] getCommConfigLocationId3() {
			return commConfigLocationId3;
		}

		public void setCommConfigLocationId3(String[] commConfigLocationId3) {
			this.commConfigLocationId3 = commConfigLocationId3;
		}

		public String[] getCommConfigLocationName3() {
			return commConfigLocationName3;
		}

		public void setCommConfigLocationName3(String[] commConfigLocationName3) {
			this.commConfigLocationName3 = commConfigLocationName3;
		}

		public String[] getParentItemCodess() {
			return parentItemCodess;
		}

		public void setParentItemCodess(String[] parentItemCodess) {
			this.parentItemCodess = parentItemCodess;
		}

		public String[] getParentItemNamess() {
			return parentItemNamess;
		}

		public void setParentItemNamess(String[] parentItemNamess) {
			this.parentItemNamess = parentItemNamess;
		}

		public String[] getCommConfigLocationTownId() {
			return commConfigLocationTownId;
		}

		public void setCommConfigLocationTownId(String[] commConfigLocationTownId) {
			this.commConfigLocationTownId = commConfigLocationTownId;
		}

		public String[] getCommConfigLocationTownIdname() {
			return commConfigLocationTownIdname;
		}

		public void setCommConfigLocationTownIdname(
				String[] commConfigLocationTownIdname) {
			this.commConfigLocationTownIdname = commConfigLocationTownIdname;
		}

		public String[] getCommClvId() {
			return commClvId;
		}

		public void setCommClvId(String[] commClvId) {
			this.commClvId = commClvId;
		}

		public String[] getCommClvIdname() {
			return commClvIdname;
		}

		public void setCommClvIdname(String[] commClvIdname) {
			this.commClvIdname = commClvIdname;
		}

		
		public List  getNode(List<String[]> dataList,String id){
			if(dataList!=null&&dataList.size()>0){
				List<HashMap<String, Object>>  list1 = this.hspConfigBaseinfoDao.getHspInformationList(id);

				for(Map map:list1){
					String[] dataArray = new String[3];
					dataArray[0] = Converter.toBlank(map.get("id"));

					dataArray[1] = Converter.toBlank(map.get("itemName"));
					dataArray[2] = Converter.toBlank(map.get("parentItemName"));
					
					
					dataList.add(dataArray);
					this.getNode(dataList,dataArray[0]);
				}
			}
			return dataList;
		}
		
		
		
		
	@Override
	public List<?> getHspInformationList(String staffHospitalCode) {
		
		System.out.println(staffHospitalCode+"=================staffHospitalCode");
		
		List<HashMap<String, Object>> list=this.hspConfigBaseinfoDao.getHspInformationList(staffHospitalCode);
		List<String[]> dataList = new ArrayList<String[]>();
		//处理map到数组
		if(list!=null&&list.size()>0){
			for(Map map:list){
				
				String[] dataArray = new String[3];
				dataArray[0] = Converter.toBlank(map.get("ID"));
				dataArray[1] = Converter.toBlank(map.get("ITEMNAME"));
				dataArray[2] = Converter.toBlank(map.get("PARENTITEMNAME"));
				dataList.add(dataArray);
				//this.getNode(dataList,dataArray[0]);
			}
		}
		return dataList;
	}

	
	
	
	@Override
	public List<?> nowHspDeptList(String staffHospitalId) {
		List<HashMap<String, Object>> list=this.hspConfigBaseinfoDao.getNowHspDeptList(staffHospitalId);
		List<String[]> dataList = new ArrayList<String[]>();
		//处理map到数组
		if(list!=null&&list.size()>0){
			for(Map map:list){
				String[] dataArray = new String[2];
				dataArray[0] = Converter.toBlank(map.get("deptCode"));
				dataArray[1] = Converter.toBlank(map.get("deptName"));
				dataList.add(dataArray);
			}
		}
		return dataList;
	}

	
	
	@Override
	//查询该机构对应的基本信息
	public void updateInit(HspConfigBaseinfoForm hosform,
			HttpServletRequest request, String staffId) {
		List<HspConfigBaseinfo> list=this.hspConfigBaseinfoDao.getHspConfigBaseinfo(hosform.getId());
		if(list!=null&&list.size()>0){
			HspConfigBaseinfo hcb=list.get(0);
			this.setForm(hosform, hcb);
			
		}
		hosform.setStaffId(staffId);
		init(hosform,request);
		this.getDetail(hosform);
	}
	public void getDetail(HspConfigBaseinfoForm form){}
	/** 在初始化阶段将字典库传入form中 */
	public void init(HspConfigBaseinfoForm form , HttpServletRequest request){
		this.getDict(form);
		// --------所有字典都定义成数组-------
		/** 医院等 */
		form.setCommConfigUnittypeIds(this.getCommConfigUnittypeId());
		form.setCommConfigUnittypeId_names(this.getCommConfigUnittypeName());
		/** 医院级*/
		form.setCommConfigUnitgradeIds(this.getCommConfigUnitgradeId());
		form.setCommConfigUnitgradeId_names(this.getCommConfigUnitgradeName());
		/** 经济性质 */
		form.setCommConfigEconkindIds(this.getCommConfigEconkindId());
		form.setCommConfigEconkindId_names(this.getCommConfigEconkindName());
		
		/** 所属省 */
		form.setCommConfigLocationId1s(this.getCommConfigLocationId1());
		form.setCommConfigLocationId1_names(this.getCommConfigLocationName1());
		/** 所属市 */
		form.setCommConfigLocationId2s(this.getCommConfigLocationId2());
		form.setCommConfigLocationId2_names(this.getCommConfigLocationName2());
		/** 所属县 */
		form.setCommConfigLocationId3s(this.getCommConfigLocationId3());
		form.setCommConfigLocationId3_names(this.getCommConfigLocationName3());
		/** 乡镇 */
		form.setCommConfigLocationTownIds(this.getCommConfigLocationTownId());
		form.setCommConfigLocationTownId_names(this.getCommConfigLocationTownIdname());
		/** 村 */
		form.setCommClvIds(this.getCommClvId());
		form.setCommClvId_names(this.getCommClvIdname());
		/** 上级医疗机构 */
		form.setParentItemCodes(this.getParentItemCodess());
		form.setParentItemCode_names(this.getParentItemNamess());
		/** 机构分类管理 */
		form.setCommConfigFtManageIds(this.getCommConfigFtManageIds());
		form.setCommConfigFtManageId_names(this.getCommConfigFtManageId_names());

	}
	/** 根据字典类别获取字典详细信息列表 */
	public void getDict(HspConfigBaseinfoForm form){
		form.setC010501aList(this.hspConfigBaseinfoDao.findByBean("Comm.Comm_Config_Unittype","seq_no","item_name"));
		// 医院级
		form.setC010501bList(this.hspConfigBaseinfoDao.findByBean("Comm.Comm_Config_Unitgrade","seq_no","item_name"));
		List<?> commConfigUnittype_data = hspConfigBaseinfoDao.findByBean("Comm.Comm_Config_Unittype","seq_no","item_name");
		List<?> commConfigUnitgrade_data = hspConfigBaseinfoDao.findByBean("Comm.Comm_Config_Unitgrade","seq_no","item_name");
		List<?> commConfigEconkind_data = hspConfigBaseinfoDao.findByBean("Comm.Comm_Config_Econkind","Item_code","item_name");
		List<?> commConfigLocationId1_data = hspConfigBaseinfoDao.getByParent("Comm.Comm_Config_Location","item_code","item_name","parent_id","", "1");
		List<?> commConfigLocationId2_data = null;
		if(form.getCommConfigLocationId1()!= null && form.getCommConfigLocationId1().trim().length()>0){
			commConfigLocationId2_data = hspConfigBaseinfoDao.getByParent("Comm.Comm_Config_Location","item_code","item_name","parent_id",form.getCommConfigLocationId1(), "2");
		}
		List<?> commConfigLocationId3_data = null;
		if(form.getCommConfigLocationId2()!=null && form.getCommConfigLocationId2().trim().length()>0){
			commConfigLocationId3_data = hspConfigBaseinfoDao.getByParent("Comm.Comm_Config_Location","item_code","item_name","parent_id",form.getCommConfigLocationId2(), "3");
		}
		List<?> commConfigLocationTownId_data = null;
		if(form.getCommConfigLocationId3()!=null && form.getCommConfigLocationId3().trim().length()>0){
			commConfigLocationTownId_data = hspConfigBaseinfoDao.getByParent("Comm.Comm_Config_Location_Town","id","item_name","comm_Config_Location_Id",form.getCommConfigLocationId3(), "");
		}
		List<?> commClvId_data = null;
		if(form.getCommClvId()!=null && form.getCommClvId().trim().length()>0){
			commClvId_data = hspConfigBaseinfoDao.getByParent("Comm.Comm_Config_Location_Village","id","item_name","comm_Clt_Id",form.getCommConfigLocationTownId(), "");
		}

		List<?> getCommConfigFtManageIds_data =hspConfigBaseinfoDao.findByBean("Comm.Comm_Config_Ft_Manage","item_code","item_name");// 机构分类管理-修改
		// ------------------------医院等----------------------------------------------------
		String[] tempId = null;
		String[] tempName = null;
		if(commConfigUnittype_data!=null){
			tempId = new String[commConfigUnittype_data.size()+1];
			tempName = new String[commConfigUnittype_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commConfigUnittype_data.size(); i++){
				CommConfigBean data =(CommConfigBean) commConfigUnittype_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getSeqNo());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}
		this.setCommConfigUnittypeId(tempId);
		this.setCommConfigUnittypeName(tempName);
		// ------------------------医院级-----------------------------------------------------
		tempId = null;
		tempName = null;
		if(commConfigUnitgrade_data!=null){
			tempId = new String[commConfigUnitgrade_data.size()+1];
			tempName = new String[commConfigUnitgrade_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commConfigUnitgrade_data.size(); i++){
				CommConfigBean data =(CommConfigBean) commConfigUnitgrade_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getSeqNo());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}
		this.setCommConfigUnitgradeId(tempId);
		this.setCommConfigUnitgradeName(tempName);
		// ------------------------民族-----------------------------------------------------
		tempId = null;
		tempName = null;
		if(commConfigEconkind_data!=null){
			tempId = new String[commConfigEconkind_data.size()+1];
			tempName = new String[commConfigEconkind_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commConfigEconkind_data.size(); i++){
				CommConfigBean data =(CommConfigBean) commConfigEconkind_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getSeqNo());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}
		this.setCommConfigEconkindId(tempId);
		this.setCommConfigEconkindName(tempName);
		// ----------------------省----------------------------------------------------
		tempId = null;
		tempName = null;
		if(commConfigLocationId1_data != null){
			tempId = new String[commConfigLocationId1_data.size()+1];
			tempName = new String[commConfigLocationId1_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commConfigLocationId1_data.size(); i++){
				CommConfigBean data =(CommConfigBean) commConfigLocationId1_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getItemCode());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}
		this.setCommConfigLocationId1(tempId);
		this.setCommConfigLocationName1(tempName);
		// ----------------------市----------------------------------------------------
		tempId = null;
		tempName = null;
		if(commConfigLocationId2_data!=null){
			tempId = new String[commConfigLocationId2_data.size()+1];
			tempName = new String[commConfigLocationId2_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commConfigLocationId2_data.size(); i++){
				CommConfigBean data =(CommConfigBean) commConfigLocationId2_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getItemCode());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}else{
			// --------------如果为空，插入空格-----------
			tempId = new String[1];
			tempName = new String[1];
			tempId[0] = "";
			tempName[0] = "";
		}
		this.setCommConfigLocationId2(tempId);
		this.setCommConfigLocationName2(tempName);
		// ----------------------县----------------------------------------------------
		tempId = null;
		tempName = null;
		if(commConfigLocationId3_data!=null){
			tempId = new String[commConfigLocationId3_data.size() + 1];
			tempName = new String[commConfigLocationId3_data.size() + 1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commConfigLocationId3_data.size(); i++){
				CommConfigBean data =(CommConfigBean) commConfigLocationId3_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getItemCode());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}else{
			// --------------如果为空，插入空格-----------
			tempId = new String[1];
			tempName = new String[1];
			tempId[0] = "";
			tempName[0] = "";
		} 
		this.setCommConfigLocationId3(tempId);
		this.setCommConfigLocationName3(tempName);

		// ----------------------乡镇----------------------------------------------------
		tempId = null;
		tempName = null;
		if(commConfigLocationTownId_data!=null){
			tempId = new String[commConfigLocationTownId_data.size()+1];
			tempName = new String[commConfigLocationTownId_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commConfigLocationTownId_data.size(); i++){
				CommConfigBean data =(CommConfigBean) commConfigLocationTownId_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getItemCode());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}else{
			// --------------如果为空，插入空格-----------
			tempId = new String[1];
			tempName = new String[1];
			tempId[0] = "";
			tempName[0] = "";
		}
		this.setCommConfigLocationTownId(tempId);
		this.setCommConfigLocationTownIdname(tempName);
		// ----------------------村----------------------------------------------------
		tempId = null;
		tempName = null;
		if(commClvId_data!=null){
			tempId = new String[commClvId_data.size()+1];
			tempName = new String[commClvId_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commClvId_data.size(); i++){
				CommConfigBean data =(CommConfigBean) commClvId_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getItemCode());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}else{
			// --------------如果为空，插入空格-----------
			tempId = new String[1];
			tempName = new String[1];
			tempId[0] = "";
			tempName[0] = "";
		}
		form.setCommClvIds(tempId);
		form.setCommClvId_names(tempName);
		this.setCommClvId(tempId);
		this.setCommClvIdname(tempName);

		// ----------------------机构分类管理_修改后----------------------------------------------------
		tempId = null;
		tempName = null;
		if(getCommConfigFtManageIds_data != null){
			tempId = new String[getCommConfigFtManageIds_data.size()+1];
			tempName = new String[getCommConfigFtManageIds_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<getCommConfigFtManageIds_data.size(); i++){
				CommConfigBean data =(CommConfigBean) getCommConfigFtManageIds_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getSeqNo());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}else{
			// --------------如果为空，插入空格-----------
			tempId = new String[1];
			tempName = new String[1];
			tempId[0] = "";
			tempName[0] = "";
		}
		List<?> list=hspConfigBaseinfoDao.findByBean("Comm.Comm_Config_Settype","item_code","item_name");
		if(list.size()>0&&list!=null){
			String[] settypeCode=new String[list.size()];
			String[] settypeNmae=new String[list.size()];
			for(int i=0;i<list.size();i++){
				CommConfigBean ccs=(CommConfigBean)list.get(i);
				settypeCode[i+0]=Converter.toBlank(ccs.getSeqNo());
				settypeNmae[i+0]=Converter.toBlank(ccs.getItemName());
			}
			form.setCommConfigSetTypeId(settypeCode);
			form.setCommConfigSetType(settypeNmae);
		}
		
		List<?> list1=hspConfigBaseinfoDao.findByBean("Comm.Comm_Dict_Public_Char where class_Code='N200711_T1-1_1.4.3'","id","dict_value");
		if(list.size()>0&&list!=null){
			String[] settypeCode=new String[list1.size()];
			String[] settypeNmae=new String[list1.size()];
			for(int i=0;i<list1.size();i++){
				CommConfigBean ccs=(CommConfigBean)list1.get(i);
				settypeCode[i+0]=Converter.toBlank(ccs.getSeqNo());
				settypeNmae[i+0]=Converter.toBlank(ccs.getItemName());
			}
			form.setCommConfigCovaffrsId(settypeCode);
			form.setCommConfigCovaffrs(settypeNmae);
		}
		this.setCommConfigFtManageIds(tempId);
		this.setCommConfigFtManageId_names(tempName);
	}
	/**
	 * form与实体之间转化
	 * @param form
	 * @param data
	 */
	private void setForm(HspConfigBaseinfoForm form, HspConfigBaseinfo data){
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			form.setId(Converter.toBlank(data.getId()));
			form.setItemCode(Converter.toBlank(data.getItemCode()));
			form.setItemName(Converter.toBlank(data.getItemName()));
			form.setParentItemCode(Converter.toBlank(data.getParentItemCode()));
			form.setCommConfigUnittypeId(Converter.toBlank(data.getCommConfigUnittypeId()));
			form.setCommConfigUnitgradeId(Converter.toBlank(data.getCommConfigUnitgradeId()));
			form.setCommConfigEconkindId(Converter.toBlank(data.getCommConfigEconkindId()));
			form.setCommConfigLocationId1(Converter.toBlank(data.getCommConfigLocationId1()));
			form.setCommConfigLocationId2(Converter.toBlank(data.getCommConfigLocationId2()));
			form.setCommConfigLocationId3(Converter.toBlank(data.getCommConfigLocationId3()));
			form.setCommConfigLocationTownId(Converter.toBlank(data.getCommConfigLocationTownId()));
			form.setCommClvId(Converter.toBlank(data.getCommClvId()));
			form.setAddress(Converter.toBlank(data.getAddress()));
			form.setZipcode(Converter.toBlank(data.getZipcode()));
			form.setContactPersonName(Converter.toBlank(data.getContactPersonName()));
			form.setPhone(Converter.toBlank(data.getPhone()));
			form.setDateClinicNum(Converter.toBlank(data.getDateClinicNum()));
			form.setYearOuthospitalNum(Converter.toBlank(data.getYearOuthospitalNum()));
			form.setAuthorizedBedNum(Converter.toBlank(data.getAuthorizedBedNum()));
			form.setOutspreadBedNum(Converter.toBlank(data.getOutspreadBedNum()));
			form.setDoctorNum(Converter.toBlank(data.getDoctorNum()));
			form.setNurseNum(Converter.toBlank(data.getNurseNum()));
			form.setTechnicPersonNum(Converter.toBlank(data.getTechnicPersonNum()));
			form.setSickroomNum(Converter.toBlank(data.getSickroomNum()));
			form.setOperationroomNum(Converter.toBlank(data.getOperationroomNum()));
			data.setOperationroomNum(data.getOperationroomNum()==null ? null : Long.valueOf(data.getOperationroomNum()));
			form.setComments(Converter.toBlank(data.getComments()));
			form.setSeqNo(data.getSeqNo()==null ? "0" : String.valueOf(data.getSeqNo()));
			form.setInputCode(Converter.toBlank(data.getInputCode()));
			form.setLevelDesc(Converter.toBlank(data.getLevelDesc()));
			form.setCreateUserId(Converter.toBlank(data.getCreateUserId()));
			if(Converter.toBlank(data.getCreateDate())!=null&&Converter.toBlank(data.getCreateDate()).length()>0){
			form.setCreateDate(sdf.format(Converter.toDate(Converter.toBlank(data.getCreateDate()))));
			}else{
				form.setCreateDate("");
			}
			try{
				if(data.getCreateDate()!=null){
					form.setCreateDate(Converter.toBlank(data.getCreateDate()));
				}
			}catch(RuntimeException e){
				e.printStackTrace();;
			}

			form.setCommConfigFtManageId(Converter.toBlank(data.getCommConfigFtManageId()));
			form.setCommConfigHospitalTypeId(Converter.toBlank(data.getCommConfigHospitalTypeId()));
			form.setEMail(Converter.toBlank(data.getEMail()));
			form.setDomainName(Converter.toBlank(data.getDomainName()));
			//以下是表结构的冗余字段，如果该字段值为空，则根据关联获取其值，否则直接使用该值
			if(data.getCchtName()==null || data.getCchtName().trim().length()<=0){
				form.setCchtName(Converter.toBlank(this.hspConfigBaseinfoDao.getNameById("Comm.Comm_Config_Hospital_Type", "item_Name","item_code", Converter.toBlank(data.getCommConfigHospitalTypeId()))));
				
			}else{
				form.setCchtName(Converter.toBlank(data.getCchtName()));
			}
			if(data.getCommConfigFtManageName()==null || data.getCommConfigFtManageName().trim().length()<=0){
				form.setCommConfigFtManageName(Converter.toBlank(this.hspConfigBaseinfoDao.getNameById("Comm.Comm_Config_Ft_Manage", "item_Name","item_Code",Converter.toBlank(data.getCommConfigFtManageId()))));
			}else{
				form.setCommConfigFtManageName(Converter.toBlank(data.getCommConfigFtManageName()));
			}
			if(data.getCommClvName()==null || data.getCommClvName().trim().length()<=0){
				form.setCommClvName(Converter.toBlank(this.hspConfigBaseinfoDao.getNameById("Comm.Comm_Config_Location_Village", "item_Name", "id", Converter.toBlank(data.getCommClvId()))));
			}else{
				form.setCommClvName(Converter.toBlank(data.getCommClvName()));
			}
			if(data.getCommConfigEconkindName()==null || data.getCommConfigEconkindName().trim().length()<=0){
				form.setCommConfigEconkindName(Converter.toBlank(this.hspConfigBaseinfoDao.getNameById("Comm.Comm_Config_Econkind", "item_Name", "item_code", Converter.toBlank(data.getCommConfigEconkindId()))));
			}else{
				form.setCommConfigEconkindName(Converter.toBlank(data.getCommConfigEconkindName()));
			}
			if(data.getCommConfigLocationName1()==null || data.getCommConfigLocationName1().trim().length()<=0){
				form.setCommConfigLocationName1(Converter.toBlank(this.hspConfigBaseinfoDao.getNameById("Comm.Comm_Config_Location", "item_Name", "item_code", Converter.toBlank(data.getCommConfigLocationId1()))));
			}else{
				form.setCommConfigLocationName1(Converter.toBlank(data.getCommConfigLocationName1()));
			}
			if(data.getCommConfigLocationName2()==null || data.getCommConfigLocationName2().trim().length()<=0){
				form.setCommConfigLocationName2(Converter.toBlank(this.hspConfigBaseinfoDao.getNameById("Comm.Comm_Config_Location", "item_Name", "item_code", Converter.toBlank(data.getCommConfigLocationId2()))));
			}else{
				form.setCommConfigLocationName2(Converter.toBlank(data.getCommConfigLocationName2()));
			}
			if(data.getCommConfigLocationName3()==null || data.getCommConfigLocationName3().trim().length()<=0){
				form.setCommConfigLocationName3(Converter.toBlank(this.hspConfigBaseinfoDao.getNameById("Comm.Comm_Config_Location", "item_Name", "item_code", Converter.toBlank(data.getCommConfigLocationId3()))));
			}else{
				form.setCommConfigLocationName3(Converter.toBlank(data.getCommConfigLocationName3()));
			}
			if(data.getCcltName()==null || data.getCcltName().trim().length()<=0){
				form.setCcltName(Converter.toBlank(this.hspConfigBaseinfoDao.getNameById("Comm.Comm_Config_Location_Town", "item_Name", "id", Converter.toBlank(data.getCommConfigLocationTownId()))));
			}else{
				form.setCcltName(Converter.toBlank(data.getCcltName()));
			}
			if(data.getCommClvName()==null || data.getCommClvName().trim().length()<=0){
				form.setCommClvName(Converter.toBlank(this.hspConfigBaseinfoDao.getNameById("Comm.Comm_Config_Location_Village", "item_Name", "id", Converter.toBlank(data.getCommClvId()))));
			}else{
				form.setCommClvName(Converter.toBlank(data.getCommClvName()));
			}
			if(data.getCommConfigUnitgradeName()==null || data.getCommConfigUnitgradeName().trim().length()<=0){
				form.setCommConfigUnitgradeName(Converter.toBlank(this.hspConfigBaseinfoDao.getNameById("Comm.Comm_Config_Unitgrade", "item_Name", "seq_No", Converter.toBlank(data.getCommConfigUnitgradeId()))));
			}else{
				form.setCommConfigUnitgradeName(Converter.toBlank(data.getCommConfigUnitgradeName()));
			}
			if(data.getCommConfigUnittypeName()==null || data.getCommConfigUnittypeName().trim().length()<=0){
				form.setCommConfigUnittypeName(Converter.toBlank(this.hspConfigBaseinfoDao.getNameById("Comm.Comm_Config_Unittype", "item_Name", "seq_no", Converter.toBlank(data.getCommConfigUnittypeId()))));
			}else{
				form.setCommConfigUnittypeName(Converter.toBlank(data.getCommConfigUnittypeName()));
			}
			if(data.getCreateUserName()==null || data.getCreateUserName().trim().length()<=0){
				form.setCreateUserName(Converter.toBlank(this.hspConfigBaseinfoDao.getNameById("Security.Security_Staff_Baseinfo", "name", "id", Converter.toBlank(data.getCreateUserId()))));
			}else{
				form.setCreateUserName(Converter.toBlank(data.getCreateUserName()));
			}
			if(data.getParentItemName()==null || data.getParentItemName().trim().length()<=0){
				form.setParentItemName(Converter.toBlank(this.hspConfigBaseinfoDao.getNameById("hsp.Hsp_Config_Baseinfo", "item_Name", "item_Code", Converter.toBlank(data.getParentItemCode()))));
			}else{
				form.setParentItemName(Converter.toBlank(data.getParentItemName()));
			}
			form.setCommConfigCovaffrss(Converter.toBlank(data.getCommConfigGovaffrsId()));
			form.setCommConfigSetTypes(Converter.toBlank(data.getCommConfigSettypeId()));
		} catch(Exception e){
			e.printStackTrace();;
		}
	}

	@Override
	public String getInputCode(String name) {
		try{
			String temp = "";
			if(name != null && name.trim().length() > 0){
				String subStr = name.trim();
				while(subStr.length() > 0){
					String first = subStr.substring(0, 1);
					temp += this.hspConfigBaseinfoDao.getNameById("Comm.Comm_Config_Input_Dict", "input_Code", "item_Name", first);
					subStr = subStr.substring(1);
				}
			}
			
			temp = temp.toUpperCase();
	        if(temp.length() > 8){
	        	temp = temp.substring(0, 8);
	        }
	       
			return temp;
			}catch(Exception e){
				e.printStackTrace();
				return "";
			}
	}

	@Override
	public void update(HspConfigBaseinfoForm hosform) {
		
		HspConfigBaseinfo data = new HspConfigBaseinfo();
		System.out.println(hosform.getId()+","+hosform.getItemCode());
		this.setData(hosform, data);
		this.hspConfigBaseinfoDao.update(data);
	}

	private void setData(HspConfigBaseinfoForm form, HspConfigBaseinfo data) {
		try{
			data.setId(Converter.toBlank(form.getId()));
			data.setItemCode(Converter.toBlank(form.getItemCode()));
			data.setItemName(Converter.toBlank(form.getItemName()));
			data.setParentItemCode(Converter.toBlank(form.getParentItemCode()));
			data.setParentItemName(Converter.toBlank(form.getParentItemCode_name()));
			data.setCommConfigUnittypeId(Converter.toBlank(form.getCommConfigUnittypeId()));
			data.setCommConfigUnitgradeId(Converter.toBlank(form.getCommConfigUnitgradeId()));
			data.setCommConfigEconkindId(Converter.toBlank(form.getCommConfigEconkindId()));
			data.setCommConfigLocationId1(Converter.toBlank(form.getCommConfigLocationId1()));
			data.setCommConfigLocationId2(Converter.toBlank(form.getCommConfigLocationId2()));
			data.setCommConfigLocationId3(Converter.toBlank(form.getCommConfigLocationId3()));
			data.setCommConfigLocationTownId(Converter.toBlank(form.getCommConfigLocationTownId()));
			data.setCommClvId(Converter.toBlank(form.getCommClvId()));
			data.setAddress(Converter.toBlank(form.getAddress()));
			data.setZipcode(Converter.toBlank(form.getZipcode()));
			data.setContactPersonName(Converter.toBlank(form.getContactPersonName()));
			data.setPhone(Converter.toBlank(form.getPhone()));
			data.setDateClinicNum(!((Converter.toBlank(form.getDateClinicNum())).trim().length()>0) ? null : Long.valueOf(form.getDateClinicNum()));
			data.setYearOuthospitalNum(!((Converter.toBlank(form.getYearOuthospitalNum())).trim().length()>0) ? null : Long.valueOf(form.getYearOuthospitalNum()));
			data.setAuthorizedBedNum(!((Converter.toBlank(form.getAuthorizedBedNum())).trim().length()>0) ? null : Long.valueOf(form.getAuthorizedBedNum()));
			data.setOutspreadBedNum(!((Converter.toBlank(form.getOutspreadBedNum())).trim().length()>0) ? null : Long.valueOf(form.getOutspreadBedNum()));
			data.setDoctorNum(!((Converter.toBlank(form.getDoctorNum())).trim().length()>0) ? null : Long.valueOf(form.getDoctorNum()));
			data.setNurseNum(!((Converter.toBlank(form.getNurseNum())).trim().length()>0) ? null : Long.valueOf(form.getNurseNum()));
			data.setTechnicPersonNum(!((Converter.toBlank(form.getTechnicPersonNum())).trim().length()>0) ? null : Long.valueOf(form.getTechnicPersonNum()));
			data.setSickroomNum(!((Converter.toBlank(form.getSickroomNum())).trim().length()>0) ? null : Long.valueOf(form.getSickroomNum()));
			data.setOperationroomNum(!((Converter.toBlank(form.getOperationroomNum())).trim().length()>0) ? null : Long.valueOf(form.getOperationroomNum()));
			data.setLevelDesc(Converter.toBlank(form.getLevelDesc()));
			data.setCreateUserId(Converter.toBlank(form.getCreateUserId()));
			data.setCchtName(Converter.toBlank(form.getCchtName()));
			data.setCreateUserName(Converter.toBlank(form.getCreateUserName()));
			data.setCreateDate(Converter.toDate(form.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
			data.setCommConfigFtManageId(Converter.toBlank(form.getCommConfigFtManageId()));
			data.setCommConfigHospitalTypeId(Converter.toBlank(form.getCommConfigHospitalTypeId()));
			try{
				String parentLevelDesc = this.hspConfigBaseinfoDao.getP2ValueByP1(Converter.toBlank(form.getParentItemCode()));
				if(parentLevelDesc!=null && parentLevelDesc.trim().length()>0){
					data.setLevelDesc(parentLevelDesc+Converter.toBlank(form.getItemCode()) + "\\");
				}else{
					data.setLevelDesc("\\"+Converter.toBlank(form.getItemCode())+"\\");
				}
			} catch(Exception e){
				e.printStackTrace();;
				data.setLevelDesc("\\" + Converter.toBlank(form.getItemCode())+"\\");
			}
			data.setComments(Converter.toBlank(form.getComments()));	
			if(form.getSeqNo()!=null&&form.getSeqNo().trim()!=""){
				try {
					data.setSeqNo(Long.valueOf(form.getSeqNo()));
				} catch (Exception e) {
					data.setSeqNo(0L);
				}
			}else{
				Long s=this.hspConfigBaseinfoDao.seqNoMaker();
				if(s!=null&&s>0){
					data.setSeqNo(s+1);
				}else{
					data.setSeqNo((long) 1);
				}
				
			}
			
			data.setInputCode(Converter.toBlank(form.getInputCode()));
			data.setEMail(Converter.toBlank(form.getEMail()));
			data.setDomainName(Converter.toBlank(form.getDomainName()));
			data.setHspType(Long.valueOf("1"));
			data.setCommConfigSettypeId(Converter.toBlank(form.getCommConfigSetTypes()));
			data.setCommConfigGovaffrsId(Converter.toBlank(form.getCommConfigCovaffrss()));
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void getItemCode(CommConfigLocationCountyForm form) {
		
		if(form.getCommProvinceId()!=null&&form.getCommProvinceId().length()>0){
			String[] tempIds = null;
			String[] tempNames = null;
			List<?> list = null;
			list=this.hspConfigBaseinfoDao.getByParent("Comm.Comm_config_location", "item_code", "item_name", "parent_id", form.getCommProvinceId(), "");
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigBean location = (CommConfigBean)list.get(i);
					tempIds[i+1] = Converter.toBlank(location.getItemCode());
					tempNames[i+1] = Converter.toBlank(location.getItemName());
				}
				form.setCommCityIds(tempIds);
				form.setCommCityNames(tempNames);
			}
		}else if(form.getCommCityId()!=null&&form.getCommCityId().length()>0){
			String[] tempIds = null;
			String[] tempNames = null;
			List<?> list = null;
			list=this.hspConfigBaseinfoDao.getByParent("Comm.Comm_config_location", "item_code", "item_name", "parent_id", form.getCommCityId(), "");
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigBean location = (CommConfigBean)list.get(i);
					tempIds[i+1] = Converter.toBlank(location.getItemCode());
					tempNames[i+1] = Converter.toBlank(location.getItemName());
				}
				form.setCommCountyIds(tempIds);
				form.setCommCountyNames(tempNames);
			}
		}else if(form.getCommCountyId()!=null&&form.getCommCountyId().length()>0){
			String[] tempIds = null;
			String[] tempNames = null;
			List<?> list = null;
			list=this.hspConfigBaseinfoDao.getByParent("Comm.Comm_Config_Location_Town","id","item_name","comm_Config_Location_Id", form.getCommCountyId(), "");
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigBean location = (CommConfigBean)list.get(i);
					tempIds[i+1] = Converter.toBlank(location.getItemCode());
					tempNames[i+1] = Converter.toBlank(location.getItemName());
				}
				form.setCommCltIds(tempIds);
				form.setCommCltNames(tempNames);
			}
		}else if(form.getCommCltId()!=null&&form.getCommCltId().length()>0){
			String[] tempIds = null;
			String[] tempNames = null;
			List<?> list = null;
			list=this.hspConfigBaseinfoDao.getByParent("Comm.Comm_Config_Location_Village","id","item_name","comm_Clt_Id", form.getCommCltId(), "");
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigBean location = (CommConfigBean)list.get(i);
					tempIds[i+1] = Converter.toBlank(location.getItemCode());
					tempNames[i+1] = Converter.toBlank(location.getItemName());
				}
				form.setCommClvIds(tempIds);
				form.setCommClvNames(tempNames);
			}
		}
		
		
	}

	@Override
	public void addInit(HspConfigBaseinfoForm hosform,
			HttpServletRequest request, String staffId) {
		String useForTree = request.getParameter("useForTree");
		if("1".equals(useForTree)){
			String tree_hspId = request.getParameter("tree_hspId");
			if(tree_hspId != null){
				List<HspConfigBaseinfo> list =  hspConfigBaseinfoDao
						.getHspConfigBaseinfo(tree_hspId);//.getByParent("hsp.hsp_config_baseinfo", "item_code", "item_name", "id", tree_hspId, "");
				if(list.size()>0){
					HspConfigBaseinfo ccb=list.get(0);
					hosform.setParentItemCode(ccb.getItemCode());
					hosform.setParentItemName(ccb.getItemName());
				}
			}
		}
		hosform.setStaffId(staffId);
		init(hosform,request);
	}

	@Override
	//@Transactional
	public void add(HspConfigBaseinfoForm hosform) {
		HspConfigBaseinfo data = new HspConfigBaseinfo();
		System.out.println(hosform.getId()+","+hosform.getItemCode());
		hosform.setId(Converter.toBlank(hosform.getItemCode()));
		data.setCreateDate(new Date());
		this.setData(hosform, data);
		//this.hspConfigBaseinfoDao.add1("1001","测试");
		this.hspConfigBaseinfoDao.add(data);
		
		System.out.println("返回成功");
		
	}

	@Override
	public void detele(String hspId, String deptCode) {
		if(Converter.toBlank(deptCode)!=null&&Converter.toBlank(deptCode).length()>0){//删除机构下面的科室
			this.hspConfigBaseinfoDao.delete("hsp.hsp_dept_baseinfo","HSP_CONFIG_BASEINFO_ID",hspId,"DEPT_CODE",deptCode);
		}else{//删除机构
			this.hspConfigBaseinfoDao.delete("hsp.hsp_config_baseinfo","id",hspId,"","");
		}
		
	}

	@Override
	public void getUpdateDeptInit(HealthRegisterTreeForm hrtForm) {
		List<HspDeptBaseinfo> list=this.hspConfigBaseinfoDao.getHspDeptBaseinfo(Converter.toBlank(hrtForm.getHspId()),Converter.toBlank(hrtForm.getDeptCode()));
		if(list!=null&&list.size()>0){
			HspDeptBaseinfo hdb=list.get(0);
			hrtForm.setHspId(Converter.toBlank(hdb.getHspConfigBaseinfoId()));
			hrtForm.setHspConfigBaseinfoName(Converter.toBlank(hdb.getHspConfigBaseinfoName()));
			hrtForm.setSeqNo(Converter.toLong(hdb.getSeqNo()));
			hrtForm.setDeptAttrCode(Converter.toBlank(hdb.getDeptAttrCode()));
			hrtForm.setDeptAttrName(Converter.toBlank(hdb.getDeptAttrName()));
			hrtForm.setHealthBureauCode(Converter.toBlank(hdb.getHealthBureauCode()));
			hrtForm.setSocialSecurityBureauCode(Converter.toBlank(hdb.getSocialSecurityBureauCode()));
			hrtForm.setDeptCode(Converter.toBlank(hdb.getDeptCode()));
			hrtForm.setDeptName(Converter.toBlank(hdb.getDeptName()));
			hrtForm.setComments(Converter.toBlank(hdb.getComments()));
			hrtForm.setOutpOrInp(Converter.toBlank(hdb.getOutpOrInp()));
			hrtForm.setInternalOrSergery(Converter.toBlank(hdb.getInternalOrSergery()));
		}
		this.initDept(hrtForm);
	}
	private void initDept(HealthRegisterTreeForm hrtForm){
		
		List<CommConfigBean> list = (List<CommConfigBean>)this.hspConfigBaseinfoDao.findByBean("Comm.Comm_Config_Dept_Attr", "item_Code", "item_name");
		LinkedHashMap<String, String> deptAttrMap = new LinkedHashMap<String, String>();
		if(list != null){
			for(CommConfigBean obj : list){
				deptAttrMap.put(Converter.toBlank(obj.getSeqNo()), Converter.toBlank(obj.getItemName()));
			}
		}
		hrtForm.setDeptAttrMap(deptAttrMap);
	}

	@Override
	public void getUpdateDept(HealthRegisterTreeForm hrtForm) {
		if(isNotNull(hrtForm.getHspId()) && isNotNull(hrtForm.getDeptCode())){
			List<HspDeptBaseinfo> list=this.hspConfigBaseinfoDao.getHspDeptBaseinfo(Converter.toBlank(hrtForm.getHspId()),Converter.toBlank(hrtForm.getDeptCode()));
			if(list!=null&&list.size()>0){
				HspDeptBaseinfo hdb=list.get(0);
			if(hdb == null){
				hdb = new HspDeptBaseinfo();
			}
			//HspDeptBaseinfoId id = new HspDeptBaseinfoId();
			hdb.setHspConfigBaseinfoId(hrtForm.getHspId());
			hdb.setDeptCode(hrtForm.getDeptCode());
			hdb.setSeqNo(hrtForm.getSeqNo());
			hdb.setDeptAttrCode(hrtForm.getDeptAttrCode());
			hdb.setDeptAttrName(hrtForm.getDeptAttrName());
			hdb.setInputCode(hrtForm.getInputCode());
			hdb.setComments(hrtForm.getComments());
			hdb.setHspConfigBaseinfoName(hrtForm.getHspConfigBaseinfoName());
			hdb.setDeptName(hrtForm.getDeptName());
			hdb.setHealthBureauCode(hrtForm.getHealthBureauCode());
			hdb.setSocialSecurityBureauCode(hrtForm.getSocialSecurityBureauCode());
			hdb.setOutpOrInp(hrtForm.getOutpOrInp());
			hdb.setInternalOrSergery(hrtForm.getInternalOrSergery());
			this.hspConfigBaseinfoDao.updateDept(hdb);
			}
		}
		this.initDept(hrtForm);
		
	}
	
	private boolean isNotNull(String temp){
		return temp != null && temp.trim().length() > 0;
	}

	@Override
	public void getAddDeptInit(HealthRegisterTreeForm hrtForm) {
		String maxSeqNo = this.hspConfigBaseinfoDao.getMaxSeqNo("hsp.hsp_dept_baseinfo", "seq_no");
		Long seqNo = Long.valueOf((maxSeqNo != null && maxSeqNo.trim().length() > 0) ? maxSeqNo : "0") + 1;
		hrtForm.setSeqNo(seqNo);
		String itemName=this.hspConfigBaseinfoDao.getNameById("hsp.Hsp_Config_Baseinfo", "item_name", "item_code", hrtForm.getHspId());
		hrtForm.setHspConfigBaseinfoName(itemName);
		this.initDept(hrtForm);
	}

	@Override
	public void getAddDept(HealthRegisterTreeForm hrtForm) {
		if(isNotNull(hrtForm.getHspId()) && isNotNull(hrtForm.getDeptCode())){
			HspDeptBaseinfo hdb=new HspDeptBaseinfo();
			//HspDeptBaseinfoId id = new HspDeptBaseinfoId();
			hdb.setHspConfigBaseinfoId(hrtForm.getHspId());
			hdb.setDeptCode(hrtForm.getDeptCode());
			hdb.setSeqNo(hrtForm.getSeqNo());
			hdb.setDeptAttrCode(hrtForm.getDeptAttrCode());
			hdb.setDeptAttrName(hrtForm.getDeptAttrName());
			hdb.setInputCode(hrtForm.getInputCode());
			hdb.setComments(hrtForm.getComments());
			hdb.setHspConfigBaseinfoName(hrtForm.getHspConfigBaseinfoName());
			hdb.setDeptName(hrtForm.getDeptName());
			hdb.setHealthBureauCode(hrtForm.getHealthBureauCode());
			hdb.setSocialSecurityBureauCode(hrtForm.getSocialSecurityBureauCode());
			hdb.setOutpOrInp(hrtForm.getOutpOrInp());
			hdb.setInternalOrSergery(hrtForm.getInternalOrSergery());
			this.hspConfigBaseinfoDao.addDept(hdb);
		}
		this.initDept(hrtForm);
		
	}
	
	@Override
	public String getHospitalTreeNode(){
		List<HashMap<String,Object>> hospitalNodes = this.hspConfigBaseinfoDao.getHospital();
		StringBuilder sb = new StringBuilder("[");
		if(hospitalNodes!=null&&hospitalNodes.size()>0){
			for(Map map:hospitalNodes){
				sb.append(obj2json(map));
				sb.append(",");
			}
		}
		if(sb.charAt(sb.length() - 1) == ','){
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		return sb.toString();
	}
	
	private Object obj2json(Map map) {
		if(map != null ){
			StringBuilder sb = new StringBuilder("{id:'").append(Converter.toBlank(map.get("id")))
				.append("', pId:'").append(Converter.toBlank(map.get("parent_item_code")))
				.append("', name:'").append(Converter.toBlank(map.get("itemName")))
				.append("', type:'").append(Converter.toBlank(map.get("type"))).append("'}");
			return sb;
		}
		return null;
	}

	
}
