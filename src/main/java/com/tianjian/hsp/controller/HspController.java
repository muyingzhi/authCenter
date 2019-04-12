package com.tianjian.hsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tianjian.hsp.bean.CommConfigLocationCountyForm;
import com.tianjian.hsp.bean.HealthRegisterTreeForm;
import com.tianjian.hsp.bean.HspConfigBaseinfoForm;
import com.tianjian.hsp.service.IHspConfigBaseinfoService;
import com.tianjian.login.bean.SessionForm;
import com.tianjian.util.Converter;
/**
 * 方法名前面的文件夹名称注解
 * @author wenyh
 *
 */
@Controller
@RequestMapping("/hsp")
public class HspController {
	@Resource
	private IHspConfigBaseinfoService hspConfigBaseinfoService;
	
	@RequestMapping("/getHospitalTree")	
	public String getHospitalTree(HttpServletRequest request,HttpServletResponse response,   Model model){
		
		try {
			String json = hspConfigBaseinfoService.getHospitalTreeNode();
			this.write2Response(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
	
	
	//方法名对应的方法注解(及.do前面的名称)
	//机构初始化菜单
	@RequestMapping("/healthRegisterTree")
    public String healthRegisterTree(HttpServletRequest request,Model model){
		SessionForm sessionForm = (SessionForm) request.getSession(true).getAttribute("sessionForm");
		if(sessionForm==null){
			sessionForm=new SessionForm();
		}
		List<String[]> dataList = (List<String[]>) this.hspConfigBaseinfoService.getHspInformationList(sessionForm.getStaffHospitalCode());
		
		
		List<String[]> deptList = (List<String[]>) this.hspConfigBaseinfoService.nowHspDeptList(sessionForm.getStaffHospitalId());
		request.setAttribute("dataList", dataList);
		request.setAttribute("deptList", deptList);
		request.setAttribute("staffHspId", sessionForm.getStaffHospitalId());
		request.setAttribute("staffHspName", sessionForm.getStaffHospitalName());
		return  "hsp/index";
    }
	//科室修改初始化
	@RequestMapping("/healthRegisterTreeUpdateInit")
    public String healthRegisterTreeUpdateInit(HttpServletRequest request,HealthRegisterTreeForm hrtForm){
		SessionForm sessionForm = (SessionForm) request.getSession(true).getAttribute("sessionForm");
		if(sessionForm==null){
			sessionForm=new SessionForm();
		}
		if(Converter.toBlank(hrtForm.getType())!=null&&Converter.toBlank(hrtForm.getType()).length()>0){
			if(Converter.toBlank(hrtForm.getType()).equals("dept")){
				this.hspConfigBaseinfoService.getUpdateDeptInit(hrtForm);
				hrtForm.setMessage("");
				request.setAttribute("data", hrtForm);
				return "hsp/deptModify";
			}
		}
		
		
		return "";
    }
	//科室添加初始化
	@RequestMapping("/healthRegisterTreeAddInit")
    public String healthRegisterTreeAddInit(HttpServletRequest request,HealthRegisterTreeForm hrtForm){
		SessionForm sessionForm = (SessionForm) request.getSession(true).getAttribute("sessionForm");
		if(sessionForm==null){
			sessionForm=new SessionForm();
		}
		if(Converter.toBlank(hrtForm.getType())!=null&&Converter.toBlank(hrtForm.getType()).length()>0){
			if(Converter.toBlank(hrtForm.getType()).equals("dept")){
				this.hspConfigBaseinfoService.getAddDeptInit(hrtForm);
				hrtForm.setMessage("");
				request.setAttribute("data", hrtForm);
				return "hsp/deptAdd";
			}
		}
		
		
		return "";
    }
	//科室修改
	@RequestMapping("/healthRegisterTreeUpdate")
    public String healthRegisterTreeUpdate(HttpServletRequest request,HealthRegisterTreeForm hrtForm){
		SessionForm sessionForm = (SessionForm) request.getSession(true).getAttribute("sessionForm");
		if(sessionForm==null){
			sessionForm=new SessionForm();
		}
		
		if(Converter.toBlank(hrtForm.getType())!=null&&Converter.toBlank(hrtForm.getType()).length()>0){
			if(Converter.toBlank(hrtForm.getType()).equals("dept")){
				hrtForm.setInputCode(Converter.toBlank(this.hspConfigBaseinfoService.getInputCode(hrtForm.getDeptName())));
				this.hspConfigBaseinfoService.getUpdateDept(hrtForm);
				hrtForm.setMessage("更新成功");
				request.setAttribute("data", hrtForm);
				return "hsp/deptModify";
			}
		}
		
		
		return "";
    }
	//科室添加
	@RequestMapping("/healthRegisterTreeAdd")
    public String healthRegisterTreeAdd(HttpServletRequest request,HealthRegisterTreeForm hrtForm){
		SessionForm sessionForm = (SessionForm) request.getSession(true).getAttribute("sessionForm");
		if(sessionForm==null){
			sessionForm=new SessionForm();
		}
		if(Converter.toBlank(hrtForm.getType())!=null&&Converter.toBlank(hrtForm.getType()).length()>0){
			if(Converter.toBlank(hrtForm.getType()).equals("dept")){
				hrtForm.setInputCode(Converter.toBlank(this.hspConfigBaseinfoService.getInputCode(hrtForm.getDeptName())));
				this.hspConfigBaseinfoService.getAddDept(hrtForm);
				hrtForm.setMessage("添加成功");
				request.setAttribute("data", hrtForm);
				return "hsp/deptAdd";
			}
		}
		
		
		return "";
    }
	//机构修改初始化
	@RequestMapping("/hspConfigBaseinfoUpdateInit")
	public String hspConfigbaseinfoUpdateInit(HttpServletRequest request,Model model){
		HttpSession session = request.getSession (true); 
		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
		if(sessionForm==null){
			sessionForm=new SessionForm();
		}
		HspConfigBaseinfoForm hosform =new HspConfigBaseinfoForm();
		String idHidden=request.getParameter("idHidden");
		hosform.setId(idHidden);
		hosform.setIdHidden(idHidden);
		hspConfigBaseinfoService.updateInit(hosform,request,sessionForm.getStaffId());
		request.setAttribute("data", hosform);
		return  "hsp/update";
    }
	//机构添加初始化
	@RequestMapping("/hspConfigBaseinfoAddInit")
	public String hspConfigbaseinfoAddInit(HttpServletRequest request,HspConfigBaseinfoForm hosform){
		HttpSession session = request.getSession (true); 
		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
		
		if(sessionForm==null){
			sessionForm=new SessionForm();
		}
		hosform.setCommConfigLocationId1(sessionForm.getCommConfigLocationId1());
		hosform.setCommConfigLocationId2(sessionForm.getCommConfigLocationId2());
		hosform.setCommConfigLocationId3(sessionForm.getCommConfigLocationId3());
		hspConfigBaseinfoService.addInit(hosform,request,sessionForm.getStaffId());
		request.setAttribute("data", hosform);
		return  "hsp/add";
    }
	//机构添加
	@RequestMapping("/hspConfigBaseinfoAdd")
	public String hspConfigbaseinfoAdd(HttpServletRequest request,HspConfigBaseinfoForm hosform,HttpServletResponse response){
		HttpSession session = request.getSession (true);
		String useForTree = request.getParameter("useForTree");
		try{
		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
		if(sessionForm==null){
			sessionForm=new SessionForm();
		}
		hosform.setCreateUserId(Converter.toBlank(sessionForm.getStaffId()));
		hosform.setCreateUserName(Converter.toBlank(sessionForm.getStaffName()));
		hosform.setInputCode(this.hspConfigBaseinfoService.getInputCode(hosform.getItemName()));
		try {
			this.hspConfigBaseinfoService.add(hosform);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		String message = "添加成功";
		if("1".equals(useForTree)){
			this.write2Response(response, "[{flag:'1', msg:'"+message+"'}]");
			return null;
		}else{
			HspConfigBaseinfoForm hosformNew = new HspConfigBaseinfoForm();
			request.setAttribute("message", message);
			return  "hsp/add";
		}
	} catch(Exception e) {
		if("1".equals(useForTree)){
			this.write2Response(response, "[{flag:'0', msg:'修改失败!'}]");
			return null;
		}else{
			return "fail";
		}
	}
    }
	//机构跟科室删除
	@RequestMapping("/hspConfigBaseinfoDelete")
	public String hspConfigbaseinfoDelete(HttpServletRequest request,HspConfigBaseinfoForm hosform,HttpServletResponse response){
		HttpSession session = request.getSession (true);
		String useForTree = request.getParameter("useForTree");
		try{
		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
		if(sessionForm==null){
			sessionForm=new SessionForm();
		}
		String idHidden=request.getParameter("idHidden");
		String type=request.getParameter("type");
		String hspId=request.getParameter("hspId");
		String deptCode=request.getParameter("deptCode");
		if(Converter.toBlank(type)!=null&&Converter.toBlank(type).length()>0&&Converter.toBlank(type).equals("dept")){
			this.hspConfigBaseinfoService.detele(hspId,deptCode);	
		}else{
		this.hspConfigBaseinfoService.detele(idHidden,"");
		}
		String message = "删除成功";
		if("1".equals(useForTree)){
			this.write2Response(response, "[{flag:'1', msg:'"+message+"'}]");
			return null;
		}else{
			HspConfigBaseinfoForm hosformNew = new HspConfigBaseinfoForm();
			request.setAttribute("message", message);
			return  "hsp/add";
		}
	} catch(Exception e) {
		if("1".equals(useForTree)){
			this.write2Response(response, "[{flag:'0', msg:'修改失败!'}]");
			return null;
		}else{
			return "fail";
		}
	}
    }
	//机构修改
	@RequestMapping("/hspConfigBaseinfoUpdate")
	public String hspConfigbaseinfoUpdate(HttpServletRequest request,HspConfigBaseinfoForm hosform,HttpServletResponse response){
		HttpSession session = request.getSession (true);
		String useForTree = request.getParameter("useForTree");
		try{
		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
		if(sessionForm==null){
			sessionForm=new SessionForm();
		}
		
		hosform.setInputCode(this.hspConfigBaseinfoService.getInputCode(hosform.getItemName()));
		this.hspConfigBaseinfoService.update(hosform);
		String message = "修改成功";
		if("1".equals(useForTree)){
			this.write2Response(response, "[{flag:'1', msg:'"+message+"'}]");
			return null;
		}else{
			HspConfigBaseinfoForm hosformNew = new HspConfigBaseinfoForm();
			request.setAttribute("message", message);
			return  "hsp/update";
		}
	} catch(Exception e) {
		if("1".equals(useForTree)){
			this.write2Response(response, "[{flag:'0', msg:'修改失败!'}]");
			return null;
		}else{
			return "fail";
		}
	}
    }
	//机构中的地区级联
	@RequestMapping("/hspConfigBaseinfoCounty")
	public String hspConfigBaseinfoCounty(HttpServletRequest request,Model model,HttpServletResponse response){
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String town = request.getParameter("town");
		String village = request.getParameter("village");
		CommConfigLocationCountyForm form=new CommConfigLocationCountyForm();
		//省ID
		form.setCommProvinceId(Converter.toBlank(province));
		//市Id
		form.setCommCityId(Converter.toBlank(city));
		//县ID
		form.setCommCountyId(Converter.toBlank(town));
		//乡镇ID
		form.setCommCltId(Converter.toBlank(village));
		this.hspConfigBaseinfoService.getItemCode(form);
		String xmlString="";
		if(form.getCommProvinceId()!=null&&form.getCommProvinceId().length()>0){
			xmlString = getXMLData(form.getCommCityIds(), form.getCommCityNames());
		}else if(form.getCommCityId()!=null&&form.getCommCityId().length()>0){
			xmlString = getXMLData(form.getCommCountyIds(), form.getCommCountyNames());
		}else if(form.getCommCountyId()!=null&&form.getCommCountyId().length()>0){
			xmlString = getXMLData(form.getCommCltIds(), form.getCommCltNames());
		}else if(form.getCommCltId()!=null&&form.getCommCltId().length()>0){
			xmlString = getXMLData(form.getCommClvIds(), form.getCommClvNames());
		}
		try {
			writeResponse(response, xmlString);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return null;
		
    }
	public String getXMLData(String[] key, String[] value) {
		String xmlString = "";

		xmlString = xmlString + "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xmlString = xmlString + "<root>";
		if(key != null){
			xmlString = xmlString + "<index>" + key.length + "</index>";
			for (int i = 0; i < key.length; i++) {
				xmlString = xmlString + "<key>" + key[i] + "</key>";
				xmlString = xmlString + "<value>" + value[i] + "</value>";
			}
		} else {
			xmlString = xmlString + "<index>0</index>";
		}
		xmlString = xmlString + "</root>";

		return xmlString;
	}
	public void writeResponse(HttpServletResponse response, String xmlString) throws IOException {
		response.setContentType("text/xml");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(xmlString);
	}
	private void write2Response(HttpServletResponse response, String str){
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
