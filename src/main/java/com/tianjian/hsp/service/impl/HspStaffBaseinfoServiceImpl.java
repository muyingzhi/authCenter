package com.tianjian.hsp.service.impl;

import com.tianjian.hsp.bean.CommConfigNationality;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspStaffBaseinfo;
import com.tianjian.hsp.bean.HspStaffBaseinfoForm;
import com.tianjian.hsp.dao.IHspStaffBaseinfoDao;
import com.tianjian.hsp.service.IHspStaffBaseinfoService;
import com.tianjian.login.model.SecurityLicense;
import com.tianjian.login.model.SecurityStaffBaseinfo;
import com.tianjian.login.model.SecuritySystemUsers;
import com.tianjian.login.model.SecurityUserVsRoles;
import com.tianjian.util.Converter;
import com.tianjian.util.MD5;
import com.tianjian.util.UtilTrans;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @Author wub
 * @Create by 2018/4/7
 */
@Service("iHspStaffBaseinfoService")
public class HspStaffBaseinfoServiceImpl implements IHspStaffBaseinfoService {
	private final static DateFormat df2 = new SimpleDateFormat("yyyy-MM");
    @Resource
    private IHspStaffBaseinfoDao iHspStaffBaseinfoDao;
    
    public String getHspByStaffCode(String staffCode){
    	List<HashMap<String, Object>> hspList =this.iHspStaffBaseinfoDao.getHspByStaffCode(staffCode);
		StringBuilder sb = new StringBuilder();
    	if(hspList!=null&&hspList.size()>0){
    		Iterator<HashMap<String, Object>> ite = hspList.iterator();
    		while(ite.hasNext()){
				sb.append( "hospital_"+Converter.toBlank(ite.next().get("hspCode")) );
				if(ite.hasNext()){
					sb.append(",");
				}
    		}
    	}
    	 return sb.toString();
    }

    public String getHsp(){
    	List<HashMap<String, Object>> listRoot = this.iHspStaffBaseinfoDao.getHspRootList();
		List<Map> listHsp = new ArrayList<Map>();
		List<Map> listAll = new ArrayList<Map>();
		Map mapAll = new HashMap();
		if (listRoot != null && listRoot.size() > 0) {
			for (Map map : listRoot) {
				Map mapHsp = new HashMap();
				mapHsp.put("id",  Converter.toBlank(map.get("id")));
				mapHsp.put("text",  Converter.toBlank(map.get("itemName")));
				mapHsp.put("state", "open");
				mapHsp.put("children","");
				List<HashMap<String, Object>> list2 = this.iHspStaffBaseinfoDao.getHspInformation(Converter.toBlank(map.get("id")));
				List<Map> listHsp2 = new ArrayList<Map>();
				if (list2 != null && list2.size()> 0){ 
					for (Map map2 : list2) {
				
						Map mapHsp2 = new HashMap();
						mapHsp2.put("id", Converter.toBlank(map2.get("id")));
						mapHsp2.put("text", Converter.toBlank(map2.get("itemName")));
						mapHsp2.put("state", "open");
						mapHsp2.put("children","");
						List<HashMap<String, Object>> list3 = this.iHspStaffBaseinfoDao.getHspInformation(Converter.toBlank(map2.get("id")));
						List<Map> listHsp3 = new ArrayList<Map>();
						if (list3 != null && list3.size()> 0){ 
							for (Map map3 : list3) {
								Map mapHsp3 = new HashMap();
								mapHsp3.put("id", "hospital_"+Converter.toBlank(map3.get("id")));
								mapHsp3.put("text", Converter.toBlank(map3.get("itemName")));
								mapHsp3.put("state", "open");
								mapHsp3.put("children","");
								listHsp3.add(mapHsp3);
								mapHsp2.put("children", listHsp3);
							}
						}
						listHsp2.add(mapHsp2);
						mapHsp.put("children", listHsp2);
					}
				}
				listHsp.add(mapHsp);
				}
			}
		listAll.add(mapAll);
		String json=JSONArray.fromObject(listHsp).toString();
		return json;
    	
    }
    
    
	public List<String[]> getHspInformationList(String staffHospitalCode) {
				
		List<HashMap<String, Object>> list=(List<HashMap<String, Object>>) this.iHspStaffBaseinfoDao.getHspInformationList(staffHospitalCode);
		List<String[]> dataList = new ArrayList<String[]>();
		//处理map到数组
		if(list!=null&&list.size()>0){
			for(Map map:list){
				
				String[] dataArray = new String[3];
				dataArray[0] = Converter.toBlank(map.get("ID"));
				dataArray[1] = Converter.toBlank(map.get("ITEMNAME"));
				dataArray[2] = Converter.toBlank(map.get("PARENTITEMNAME"));
				dataList.add(dataArray);
			}
		}
		return dataList;
	}

    
    
    
    
    public List<String[]> getHspStaffInformationList(HspStaffBaseinfoForm dataForm,String hspConfigBaseinfoId) {
    	dataForm.setHspConfigBaseinfoId(hspConfigBaseinfoId);
        this.getDetail(dataForm);//获取字典
        List<HspStaffBaseinfoForm> resultList = this.iHspStaffBaseinfoDao.getHspStaffInformationList(hspConfigBaseinfoId);
        List<String[]> dataList = new ArrayList<String[]>();
        if (resultList != null && resultList.size() > 0){
            for (HspStaffBaseinfoForm hspStaffBaseinfoForm : resultList){
                String[] dataArray = new String[11];
                dataArray[0] = Converter.toBlank(hspStaffBaseinfoForm.getHspStaffBaseinfoId());
                dataArray[1] = Converter.toBlank(hspStaffBaseinfoForm.getName());
                dataArray[2] = Converter.toBlank(hspStaffBaseinfoForm.getIdNo());
                dataArray[3] = Converter.toBlank(hspStaffBaseinfoForm.getPhotoPath());
                dataArray[4] = Converter.toBlank(hspStaffBaseinfoForm.getMobileTel());
                dataArray[5] = Converter.toBlank(hspStaffBaseinfoForm.getSecurityStaffBaseinfoId());
                dataArray[6] = Converter.toBlank(hspStaffBaseinfoForm.getComments());
                dataArray[7] = Converter.toBlank(hspStaffBaseinfoForm.getMajor());
                dataArray[8] = Converter.toBlank(hspStaffBaseinfoForm.getDeptName());
				dataArray[9] = Converter.toBlank(hspStaffBaseinfoForm.getRoleDetail());
				dataArray[10] = Converter.toBlank(hspStaffBaseinfoForm.getStaffCode());
				dataList.add(dataArray);
            }
        }
        return dataList;
    }

    public List<String[]> getRoleDataList() {
        List<HspStaffBaseinfoForm> resultList = this.iHspStaffBaseinfoDao.getRoleDataList();
        List<String[]> dataList = new ArrayList<String[]>();
        if (resultList != null && resultList.size() > 0) {
            for(HspStaffBaseinfoForm hspStaffBaseinfoForm : resultList) {
                String[] dataArray = new String[2];
                dataArray[0] = Converter.toBlank(hspStaffBaseinfoForm.getId());
                dataArray[1] = Converter.toBlank(hspStaffBaseinfoForm.getRoleDetail());
                dataList.add(dataArray);
            }
        }
        return dataList;
    }

    public List<String[]> getDeptListByHspId(String staffHospitalId) {
        List<HspStaffBaseinfoForm> resultList = this.iHspStaffBaseinfoDao.getDeptListByHspId(staffHospitalId);
        List<String[]> dataList = new ArrayList<String[]>();
        if (resultList != null && resultList.size() > 0) {
            for (HspStaffBaseinfoForm hspStaffBaseinfoForm : resultList) {
                String[] array = new String[2];
                array[0]=Converter.toBlank(hspStaffBaseinfoForm.getDeptCode());
                array[1]=Converter.toBlank(hspStaffBaseinfoForm.getDeptName());
                dataList.add(array);
            }
        }
        return dataList;
    }

    public HspConfigBaseinfo getHspConfigBaseinfoNameById(String hspConfigBaseinfoId) {
        return this.iHspStaffBaseinfoDao.getHspConfigBaseinfo(hspConfigBaseinfoId);
    }

    /**
     * 获取字典
     * @param hspStaffBaseinfoForm
     */
    public void getDetail(HspStaffBaseinfoForm hspStaffBaseinfoForm) {
        hspStaffBaseinfoForm.setHspConfigBaseinfoIdList((List<HspStaffBaseinfo>) this.iHspStaffBaseinfoDao.hspConfigBaseinfoIdList(hspStaffBaseinfoForm.getHspConfigBaseinfoId()));
        hspStaffBaseinfoForm.setCommConfigSexIdList(this.iHspStaffBaseinfoDao.getSexList());
        hspStaffBaseinfoForm.setCommConfigStaffTypeIdList(this.iHspStaffBaseinfoDao.getcommConfigStaffTypeList());
    }

	@Override
	public String getHspStaffInforamtionJson(String hspStaffId) {
		List<HspStaffBaseinfo> list = this.iHspStaffBaseinfoDao.getHspStaffBaseinfo(hspStaffId);
		HspStaffBaseinfo hspStaffBaseinfo = new HspStaffBaseinfo();
		if (list != null && list.size() >0) {
			hspStaffBaseinfo = list.get(0);
		}
		
		List<HspStaffBaseinfoForm> resultList = this.iHspStaffBaseinfoDao.getHspStaffInforamtionJson(hspStaffId);
			if(resultList != null && resultList.size() > 0) {
				
				
				Map<String, String> jsonMap = new HashMap<String, String>();
				HspStaffBaseinfoForm form = resultList.get(0);
			  
				jsonMap.put("hspStaffId", hspStaffBaseinfo.getId());//卫生人员ID
				String photoPath="".equals(Converter.toBlank(hspStaffBaseinfo.getPhotoPath()))?"":hspStaffBaseinfo.getPhotoPath().replace("\\", "/");
				jsonMap.put("photoPath", photoPath);//照片路径
				jsonMap.put("name", hspStaffBaseinfo.getName());//姓名
				jsonMap.put("idNo", hspStaffBaseinfo.getIdNo());//证件号码
				jsonMap.put("commConfigSexId", hspStaffBaseinfo.getCommConfigSexId());//性别
				jsonMap.put("birthday", Converter.toString(hspStaffBaseinfo.getBirthday(), "yyyy-MM-dd"));//生日
				
				jsonMap.put("securityStaffBaseinfoId", Converter.toBlank(form.getSecurityStaffBaseinfoId()));//操作人员ID
				jsonMap.put("userName", Converter.toBlank(form.getName()));//用户名
				jsonMap.put("hspConfigBaseinfoId", Converter.toBlank(form.getHspConfigBaseinfoId()));//机构ID
				jsonMap.put("hspConfigBaseinfoName", Converter.toBlank(form.getHspConfigBaseinfoName()));//机构名称
				jsonMap.put("commConfigStaffTypeId", Converter.toBlank(form.getIdType()));//操作人员类型
				jsonMap.put("roleIds", Converter.toBlank(form.getId()));//角色集合
				
				jsonMap.put("deptCode", hspStaffBaseinfo.getRelatedDepartment());//科室代码
				jsonMap.put("mobilecode", Converter.toBlank(hspStaffBaseinfo.getMobileTel()));//电话
				jsonMap.put("major", Converter.toBlank(hspStaffBaseinfo.getMajor()));//擅长
				jsonMap.put("comments", Converter.toBlank(hspStaffBaseinfo.getComments()));//简介
				jsonMap.put("nationalityId", Converter.toBlank(hspStaffBaseinfo.getCommConfigNationalityId()));//民族Id
				CommConfigNationality commConfigNationality = this.getNationalName(hspStaffBaseinfo.getCommConfigNationalityId());
				if (commConfigNationality != null ) {
					jsonMap.put("nationalityName", commConfigNationality.getItemName());
				}
				JSONArray jsonArray = JSONArray.fromObject(jsonMap);
				return Converter.toBlank(jsonArray);
		  }
		
		return "";
	}

	private CommConfigNationality getNationalName(String commConfigNationalityId) {
		CommConfigNationality commConfigNationality = new CommConfigNationality();
		return commConfigNationality;
	}

	
	public List<HashMap<String, String>> checkHspStaff(HspStaffBaseinfoForm hspStaffBaseinfoForm){
		
		return   this.iHspStaffBaseinfoDao.checkHspStaff(hspStaffBaseinfoForm.getUserName());

	}
	
	@Override
	public void saveHspStaff(HspStaffBaseinfoForm hspStaffBaseinfoForm) {
		HspStaffBaseinfo data1 = new HspStaffBaseinfo();
		//保存卫生人员
		this.setData(hspStaffBaseinfoForm, data1);
		this.iHspStaffBaseinfoDao.saveHspStaffBaseinfo(data1);
		//保存操作人员信息
		SecurityStaffBaseinfo data2 = new SecurityStaffBaseinfo();
		this.setDateToSecurity(data1, data2);
		data2.setStaffCode(hspStaffBaseinfoForm.getUserName().trim());
		data2.setStaffType(0L);
		data2.setHspDeptBaseinfoId(hspStaffBaseinfoForm.getDeptCode());
		data2.setCommConfigStafftypeId(hspStaffBaseinfoForm.getCommConfigStaffTypeId());
		this.iHspStaffBaseinfoDao.saveSecurityStaffBaseinfo(data2);
		hspStaffBaseinfoForm.setSecurityStaffBaseinfoId(data2.getId());
		
		//保存了注册码,未对其进行操作
		String ssbId = data2.getId();
		SecurityLicense sl = new SecurityLicense();
		sl.setSecurityStaffBaseinfoId(ssbId);
		sl.setRegTime(new Date());
		String regCode = this.generateRegCode(5, 5);
		sl.setRegistCode(regCode);
		sl.setStartTime(new Date());
		sl.setStopDate(Converter.toDate("2099-01-01 00:00:00"));
		this.iHspStaffBaseinfoDao.saveSecurityLicense(sl);
		
		//保存操作人员登陆表
		SecuritySystemUsers securitySystemUsers=new SecuritySystemUsers();
		securitySystemUsers.setSecurityStaffBaseinfoId(ssbId);
		securitySystemUsers.setPasswd(MD5.toMD5(hspStaffBaseinfoForm.getUserPassword()));
		securitySystemUsers.setLicenseTag(3L);
		this.iHspStaffBaseinfoDao.saveSecuritySystemUsers(securitySystemUsers);
		
		//保存账户拥有的角色
		SecurityUserVsRoles securityUserVsRoles=new SecurityUserVsRoles();
		if(hspStaffBaseinfoForm.getRoleId() != null && hspStaffBaseinfoForm.getRoleId().length > 0){
			for (String roleId : hspStaffBaseinfoForm.getRoleId()) {
				String uuid = UUID.randomUUID().toString();
				uuid = uuid.replace("-", "");
				securityUserVsRoles.setId(uuid);
				securityUserVsRoles.setSecurityConfigRolesId(roleId);
				securityUserVsRoles.setSecurityStaffBaseinfoId(hspStaffBaseinfoForm.getSecurityStaffBaseinfoId());
				securityUserVsRoles.setComments("");
				this.iHspStaffBaseinfoDao.saveHspStaffRoles(securityUserVsRoles);
			}
		}
		
	}
	public void setData(HspStaffBaseinfoForm form, HspStaffBaseinfo data) {
		/**ID*/
		String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象  
	    uuid = uuid.replace("-", ""); //因为UUID本身为32位只是生成时多了“-”，所以将它们去点就可
		data.setId(transNullToString(uuid));
		/**组织机构ID*/
		if(form.getHspConfigBaseinfoId() != null)
			data.setHspConfigBaseinfoId(transNullToString(form.getHspConfigBaseinfoId()));
		/**人员编码(卫生局统一)*/
		
		data.setEmpNo(transNullToString(form.getEmpNo()));

		/**姓名*/
		data.setName(transNullToString(form.getName()));
		/**证件号码码*/
		data.setIdNo(transNullToString(form.getIdNo()));
		/**出生日期*/
		
		data.setBirthday(Converter.toDate(form.getBirthday()));
		/**性别*/
		data.setCommConfigSexId(transNullToString(form.getCommConfigSexId()));
		/**民族*/
		data.setCommConfigNationalityId(transNullToString(form.getCommConfigNationalityId()));
		/**参加工作日期*/
		data.setStartWorkDate(this.setStringAsDate2(Converter.toBlank(form.getStartWorkDateYear()), ""));
		/**办公室电话*/
		data.setOfficeTel(transNullToString(form.getOfficeTel()));
		/**手机号码*/
		data.setMobileTel(transNullToString(form.getMobileTel()));
		/**从事专业类别*/
		data.setCommDictPublicCharId1(transNullToString(form.getCommDictPublicCharId1()));
		/**（ 医师/ 卫生监督员）执业证书编码*/
		data.setWorkCertificateNo(transNullToString(form.getWorkCertificateNo()));
		/**医师执业类别*/
		data.setCommDictPublicCharId2(transNullToString(form.getCommDictPublicCharId2()));
		/**行政职务*/
		data.setCommConfigPositiontitleId(transNullToString(form.getCommConfigPositiontitleId()));
		/**专业技术资格（评）*/
		data.setCommConfigEmptitleId(transNullToString(form.getCommConfigEmptitleId()));
		/**专业技术职务（聘）*/
		data.setCommDictPublicCharId3(transNullToString(form.getCommDictPublicCharId3()));
		/**学历*/
		data.setCommConfigDegreeId(transNullToString(form.getCommConfigDegreeId()));
		/**学位*/
		data.setCommConfigDegreeLevelId(transNullToString(form.getCommConfigDegreeLevelId()));
		/**所学专业*/
		data.setCommConfigProfessionId(transNullToString(form.getCommConfigProfessionId()));
		/**在位标志*/
		data.setIslocation(form.getIslocation());
		/**记录日期*/
//		private Date createDate;
		/**记录人员ID*/
		data.setCreateUserId(transNullToString(form.getCreateUserId()));
		/**记录人员名称*/
		data.setCreateUserName(transNullToString(form.getCreateUserName()));
		/**所属科室*/
		data.setRelatedDepartment(transNullToString(form.getDeptCode()));
		Date d=UtilTrans.transStringToDate(form.getCreateDate(),"yyyy-MM-dd HH:mm:ss");
	
		data.setCreateDate(UtilTrans.transStringToDate(form.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
		//个人介绍
		data.setComments(form.getComments());
		//擅长
		data.setMajor(form.getMajor());
		//照片
		String photoPath="".equals(Converter.toBlank(form.getPhotoPath()))?"":form.getPhotoPath().replace("/", "\\");
		data.setPhotoPath(photoPath);
	}
	
	public void setDateToSecurity(HspStaffBaseinfo data1,SecurityStaffBaseinfo data2) {
		String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象  
	    uuid = uuid.replace("-", ""); //因为UUID本身为32位只是生成时多了“-”，所以将它们去点就可
	    data2.setId(uuid);
		// 医疗机构
		if(data1.getHspConfigBaseinfoId() != null)
			data2.setHspConfigBaseinfoId(data1.getHspConfigBaseinfoId());
		// 姓名
		if(data1.getName() != null){
			data2.setName(data1.getName());
			//String inputCodeStr = this.commConfigInputDictService.getInputCode(data1.getName());
			//输入码
			//data2.setInputCode(inputCodeStr);
		}
		if(data1.getEmpNo()!=null){
			data2.setStaffCode(data1.getEmpNo());
		}
		// 性别
		if(data1.getCommConfigSexId() != null)
			data2.setCommConfigSexId(data1.getCommConfigSexId());
		// 出生日期
		if(data1.getBirthday() != null)
			data2.setDateOfBirth(data1.getBirthday());
		// 人员编码
		if(data1.getEmpNo()!=null){
		data2.setStaffCode(data1.getEmpNo());
		}
		// 身份证件号码
		if(data1.getIdNo() != null)
			data2.setIdNo(data1.getIdNo());
		//创造时间
		// 所学专业
		// 专业技术职称
		// 职务
		// 文化程度
		// 联系电话
		String phone1 = data1.getMobileTel();
		String phone2 = data1.getOfficeTel();
		if (phone1 != null) {
			data2.setPhone(phone1);
		} else if (phone2 != null) {
			data2.setPhone(phone2);
		}
		// 在位标志
		if(data1.getIslocation() != null)
			data2.setIslocation(data1.getIslocation());
		// data2.setHspDeptBaseinfoId(data1.getDeptCode());
        if(data1.getCreateDate() != null){
        	data2.setCreateDate(data1.getCreateDate());
        }
        if(data1.getCreateUserId()!=null){
        	data2.setCreateUserId(data1.getCreateUserId());
        }
        if(data1.getCreateUserName()!=null){
        	data2.setCreateUserName(data1.getCreateUserName());
        }
        if(data1.getId()!=null){
        	data2.setHspStaffBaseinfoId(data1.getId());
        }
	}
	
	/*生成医务人员许可证注册码方法*/
	private String generateRegCode(int zushu, int weishu){
		String temp_l="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuffer reg_code = new StringBuffer();
		reg_code.append("");
		for(int i=0; i<zushu; i++){
			String temp = "";
			Random random = new Random();
			for(int j=0; j<weishu; j++)
				temp += temp_l.charAt(Math.abs(random.nextInt())%(26+26+10-1));			
			reg_code.append(temp);
			if(i != zushu-1)
				reg_code.append("-");
		}		
		return reg_code.toString().toUpperCase();
	}
	public String transNullToString(Object obj) {
		String temp = "";
		if (obj != null) {
			temp = String.valueOf(obj).trim();
		}
		return temp;
	}
	/** 将字符串的年月转化为日期类型 */
	public Date setStringAsDate2(String year, String month) {
		if (year.trim().equals("") || year == null )
			return new Date();
		String dateStr = year.trim() + "-" + month.trim();
		Date returnDate = new Date();
		df2.setLenient(false);// 这个的功能是不自动增加月到年，日到月，如不把1936-15 转换为1937-3
		try {
			returnDate = df2.parse(dateStr);
			return returnDate;
		} catch (Exception e) {
			e.printStackTrace();
			return returnDate;
		}
	}

	@Override
	public void deleteStaffByStaffId(String hspStaffId) {
		try {
			List<SecurityStaffBaseinfo> resultList = this.iHspStaffBaseinfoDao.getSecurityStaffBaseinfoId(hspStaffId); 
			if (resultList != null && resultList.size() > 0) {
				SecurityStaffBaseinfo securityStaffBaseinfo = resultList.get(0);
				String securityId = securityStaffBaseinfo.getId();
				this.iHspStaffBaseinfoDao.delete("security.security_staff_baseinfo","id",securityId,"","");
				this.iHspStaffBaseinfoDao.delete("hsp.hsp_staff_baseinfo","id",hspStaffId,"","");
				this.iHspStaffBaseinfoDao.delete("security.security_system_users","security_staff_baseinfo_id",securityId,"","");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateHspStaff(HspStaffBaseinfoForm hspStaffBaseinfoForm) {
		HspStaffBaseinfo hspStaffBaseinfo = new HspStaffBaseinfo();
		List<HspStaffBaseinfo> resultList = this.iHspStaffBaseinfoDao.getHspStaffBaseinfo(hspStaffBaseinfoForm.getId());
		if (resultList != null && resultList.size() > 0) {
			hspStaffBaseinfo = resultList.get(0);
			hspStaffBaseinfo.setName(Converter.toBlank(hspStaffBaseinfoForm.getName()));
			hspStaffBaseinfo.setIdNo(Converter.toBlank(hspStaffBaseinfoForm.getIdNo()));
			hspStaffBaseinfo.setCommConfigSexId(Converter.toBlank(hspStaffBaseinfoForm.getCommConfigSexId()));
			hspStaffBaseinfo.setBirthday(Converter.toDate(hspStaffBaseinfoForm.getBirthday()));
			hspStaffBaseinfo.setHspConfigBaseinfoId(Converter.toBlank(hspStaffBaseinfoForm.getHspConfigBaseinfoId()));
			hspStaffBaseinfo.setMobileTel(Converter.toBlank(hspStaffBaseinfoForm.getMobileTel()));
			hspStaffBaseinfo.setMajor(Converter.toBlank(hspStaffBaseinfoForm.getMajor()));
			hspStaffBaseinfo.setComments(Converter.toBlank(hspStaffBaseinfoForm.getComments()));
			hspStaffBaseinfo.setCommConfigNationalityId(Converter.toBlank(hspStaffBaseinfoForm.getCommConfigNationalityId()));
			hspStaffBaseinfo.setRelatedDepartment(Converter.toBlank(hspStaffBaseinfoForm.getDeptCode()));
			this.iHspStaffBaseinfoDao.updateHspStaffBaseinfo(hspStaffBaseinfo);
			SecurityStaffBaseinfo securityStaffBaseinfo = new SecurityStaffBaseinfo();
			List<SecurityStaffBaseinfo> securityStaffBaseinfoList = this.iHspStaffBaseinfoDao.getSecurityStaffBaseinfoId(hspStaffBaseinfoForm.getId());
			if(securityStaffBaseinfoList != null && securityStaffBaseinfoList.size() > 0){
				securityStaffBaseinfo = securityStaffBaseinfoList.get(0);
			}
			securityStaffBaseinfo.setName(Converter.toBlank(hspStaffBaseinfo.getName()));
			securityStaffBaseinfo.setStaffCode(Converter.toBlank(hspStaffBaseinfoForm.getUserName()));
			securityStaffBaseinfo.setHspConfigBaseinfoId(hspStaffBaseinfoForm.getHspConfigBaseinfoId());
			securityStaffBaseinfo.setCommConfigStafftypeId(hspStaffBaseinfoForm.getCommConfigStaffTypeId());
			securityStaffBaseinfo.setHspDeptBaseinfoId(Converter.toBlank(hspStaffBaseinfoForm.getDeptCode()));
			this.iHspStaffBaseinfoDao.updateSecurityStaffBaseinfo(securityStaffBaseinfo);
			this.iHspStaffBaseinfoDao.delete("security.security_user_vs_roles", "security_staff_baseinfo_id", securityStaffBaseinfo.getId(),"","");
			if(hspStaffBaseinfoForm.getRoleId() != null && hspStaffBaseinfoForm.getRoleId().length > 0){
				for (String roleId : hspStaffBaseinfoForm.getRoleId()) {
					SecurityUserVsRoles securityUserVsRoles = new SecurityUserVsRoles();
					String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象  
				    uuid = uuid.replace("-", ""); //因为UUID本身为32位只是生成时多了“-”，所以将它们去点就可
					securityUserVsRoles.setId(uuid);
					securityUserVsRoles.setSecurityConfigRolesId(roleId);
					securityUserVsRoles.setSecurityStaffBaseinfoId(hspStaffBaseinfoForm.getSecurityStaffBaseinfoId());
					securityUserVsRoles.setComments("");
					this.iHspStaffBaseinfoDao.saveHspStaffRoles(securityUserVsRoles);
				}
			}
			
			this.iHspStaffBaseinfoDao.delete("security.security_staff_vs_hsp", "staff_code", Converter.toBlank(hspStaffBaseinfoForm.getUserName()),"","");

		}
	}

	@Override
	public String checkIdCard(String idCard, String securityStaffBaseinfoId) {
		List<SecurityStaffBaseinfo> resultList = this.iHspStaffBaseinfoDao.checkIdCard(idCard,securityStaffBaseinfoId);
		if (resultList != null && resultList.size() > 0) {
			SecurityStaffBaseinfo securityStaffBaseinfo = resultList.get(0);
			return "{\"flag\":\"exist\",\"name\":\""+securityStaffBaseinfo.getName()+"\",\"securityStaffBaseinfoId\":\""+securityStaffBaseinfo.getId()+"\"}";
		}
		return "{\"flag\":\"\"}";
	}

	@Override
	public void deleteRoleIdOfSecurityStaff(String roleId,String securityStaffBaeinfoId) {
		this.iHspStaffBaseinfoDao.delete("security.security_user_vs_roles","security_staff_baseinfo_id",securityStaffBaeinfoId,"security_config_roles_id",roleId);
	}
}
