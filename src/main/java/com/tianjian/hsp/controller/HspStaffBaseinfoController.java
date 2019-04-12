package com.tianjian.hsp.controller;

import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspStaffBaseinfoForm;
import com.tianjian.hsp.service.IHspStaffBaseinfoService;
import com.tianjian.login.bean.SessionForm;
import com.tianjian.util.UtilTrans;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 人员信息维护Controller
 * @Author wub
 * @Create by 2018/4/7
 */
@Controller
    @RequestMapping("/hspStaffInfo")
public class HspStaffBaseinfoController {

    @Resource
    IHspStaffBaseinfoService iHspStaffBaseinfoService;
    
    
    
    
    
    
    
    @RequestMapping("/getHspByStaffCode")
    public String getHspByStaffCode(HttpServletRequest request,HttpServletResponse response) {
    	String staffCode=request.getParameter("staffCode");
    	String  json=iHspStaffBaseinfoService.getHspByStaffCode(staffCode);
		
		//write2Response(response, sb.toString());

    	
    	
    	response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");		
		PrintWriter out=null;
		try {
			out=response.getWriter();
			out.println(json);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null)
				out.close();
		}
		return null;
    }
    
    @RequestMapping("/getHsp")
    public String getHsp(HttpServletRequest request,HttpServletResponse response) {
    	String  json=iHspStaffBaseinfoService.getHsp();
		
		response.setHeader("content-type", "text/html;charset=UTF-8");
		
		PrintWriter out=null;
		try {
			out=response.getWriter();
			out.println(json);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null)
				out.close();
		}
		return null;
    }
    
    /**
     * init
     * @param request
     * @return
     */
    @RequestMapping("/hspStaffListInit")
    public String hspStaffListInit(HttpServletRequest request) {
        SessionForm sessionForm = (SessionForm) request.getSession(true).getAttribute("sessionForm");
        List<String[]> dataList = this.iHspStaffBaseinfoService.getHspInformationList(sessionForm.getStaffHospitalCode());
        request.setAttribute("dataList", dataList);
        request.setAttribute("staffHspId", sessionForm.getStaffHospitalId());
        request.setAttribute("staffHspName", sessionForm.getStaffHospitalName());
        return "hspStaffInfo/main";
    }
    /**
     * 查询机构人员
     * @param request
     * @param form
     * @return
     */
    @RequestMapping("/hspStaffList")
    public String hspStaffList(HttpServletRequest request,HspStaffBaseinfoForm form){
        SessionForm sessionForm = (SessionForm) request.getSession(true).getAttribute("sessionForm");
        String hspConfigBaseinfoId = request.getParameter("hspConfigBaseinfoId");
        String hspConfigBaseinfoId1 = sessionForm.getStaffHospitalId();
        String hspConfigBaseinfoId2 = "";
        if (hspConfigBaseinfoId == null ||hspConfigBaseinfoId.equals("")) {
        	hspConfigBaseinfoId2=hspConfigBaseinfoId1;
        	
		}else{
			hspConfigBaseinfoId2=hspConfigBaseinfoId;
		}
        
        List<String[]> hspStaffDataList = this.iHspStaffBaseinfoService.getHspStaffInformationList(form,hspConfigBaseinfoId2);
        List<String[]> roleDataList = this.iHspStaffBaseinfoService.getRoleDataList();
        List<String[]> deptList = this.iHspStaffBaseinfoService.getDeptListByHspId(hspConfigBaseinfoId2);
        HspConfigBaseinfo hspConfigBaseinfo = this.iHspStaffBaseinfoService.getHspConfigBaseinfoNameById(hspConfigBaseinfoId2);
        form.setHspConfigBaseinfoName(hspConfigBaseinfo.getItemName());
        request.setAttribute("hspStaffDataList", hspStaffDataList);
        request.setAttribute("roleDataList", roleDataList);
        request.setAttribute("deptList", deptList);
        request.setAttribute("staffHspId", sessionForm.getStaffHospitalId());
        request.setAttribute("dataForm", form);
        request.setAttribute("staffHspCode", sessionForm.getStaffHospitalCode());
        return "hspStaffInfo/hspStaffList";
    }
    /**
     * 修改init
     * @param request
     * @param form
     * @return
     */
    @RequestMapping("/updateStaffInit")
    public String updateStaffInit(HttpServletRequest request,HttpServletResponse response){
    	String hspStaffId = request.getParameter("hspStaffId");
		String json = this.iHspStaffBaseinfoService.getHspStaffInforamtionJson(hspStaffId);
		this.write2Response(response, json);
		return null;
    }
    /**
     * 保存人员信息系
     * @param request
     * @param hspStaffBaseinfoForm
     * @return
     */
    @RequestMapping("/hspStaffSave")
    public String hspStaffSave(HttpServletRequest request,HspStaffBaseinfoForm hspStaffBaseinfoForm){
		try {
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			hspStaffBaseinfoForm.setCreateUserId(sessionForm.getStaffId());
			hspStaffBaseinfoForm.setCreateUserName(sessionForm.getStaffName());
			hspStaffBaseinfoForm.setCreateDate(UtilTrans.transDateToStringFull(new Date()));			
			
			List<HashMap<String, String>> list =this.iHspStaffBaseinfoService.checkHspStaff(hspStaffBaseinfoForm);   
			if(list!=null&&list.size()>0){
				hspStaffBaseinfoForm.setMessage("存在相同的用户名！");
				return hspStaffList(request,hspStaffBaseinfoForm);

			}
			
			this.iHspStaffBaseinfoService.saveHspStaff(hspStaffBaseinfoForm);
			hspStaffBaseinfoForm.setMessage("人员信息保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			hspStaffBaseinfoForm.setMessage("人员信息保存失败！");
		}
		return hspStaffList(request,hspStaffBaseinfoForm);
    }
    /**
     * 修改人员信息
     * @param request
     * @param hspStaffBaseinfoForm
     * @return
     */
    @RequestMapping("updateHspStaff")
	public String updateHspStaff(HttpServletRequest request,HspStaffBaseinfoForm hspStaffBaseinfoForm) {
		try {String hspStaffId = request.getParameter("hspStaffId");
			
			hspStaffBaseinfoForm.setId(hspStaffId);
			this.iHspStaffBaseinfoService.updateHspStaff(hspStaffBaseinfoForm);
			hspStaffBaseinfoForm.setMessage("人员信息更新成功！");
		} catch (Exception e) {
			e.printStackTrace();
			hspStaffBaseinfoForm.setMessage("人员信息更新失败！");
		}
		return hspStaffList(request,hspStaffBaseinfoForm);
	}
	/**
	 * 删除人员信息
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping("/deleteStaff")
    public String deleteStaff(HttpServletRequest request,HttpServletResponse response,HspStaffBaseinfoForm hspStaffBaseinfoForm) {
    	try {
			String hspStaffId = request.getParameter("hspStaffId");
			this.iHspStaffBaseinfoService.deleteStaffByStaffId(hspStaffId);
			hspStaffBaseinfoForm.setMessage("人员信息删除成功！");
			//write2Response(response, "{\"flag\":\"1\",\"message\":\"删除成功\"}");
		} catch (Exception e) {
			e.printStackTrace();
			hspStaffBaseinfoForm.setMessage("人员信息删除失败！");
			//write2Response(response, "{\"flag\":\"0\",\"message\":\"删除失败\"}");
		}
		return hspStaffList(request,hspStaffBaseinfoForm);
		//return null;
    }
    /**
     * 检查身份证号是否被占用
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("idNoCheck")
    public String idNoCheck(HttpServletRequest request, HttpServletResponse response) {
		String idCard=request.getParameter("idCard");
		String securityStaffBaseinfoId=request.getParameter("securityStaffBaseinfoId");
		String json=this.iHspStaffBaseinfoService.checkIdCard(idCard,securityStaffBaseinfoId);
		System.out.println(json);
		this.write2Response(response, json);
		return null;
	}
    /**
     * 删除标签
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("deleteRoleIdOfSecurityStaff")
    public String deleteRoleIdOfSecurityStaff(HttpServletRequest request,HttpServletResponse response) {
		try {
			System.out.println("deleteRoleIdOfSecurityStaff=====================================================");
			String roleId = request.getParameter("roleId");
			String securityStaffBaeinfoId = request.getParameter("securityStaffBaseinfoId");
			this.iHspStaffBaseinfoService.deleteRoleIdOfSecurityStaff(roleId,securityStaffBaeinfoId);
			this.write2Response(response, "{\"flag\":\"1\",\"message\":\"删除成功\"}");
		} catch (Exception e) {
			e.printStackTrace();
			this.write2Response(response, "{\"flag\":\"1\",\"message\":\"删除成功\"}");
		}
		return null;
	}
	private void write2Response(HttpServletResponse response, String json){
		response.setHeader("content-type", "text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");

//		response.setHeader("Cache-Contaol", "no-store");
//		response.setHeader("Pragrma", "no-cache");
//		response.setDateHeader("Expires", 0);

		PrintWriter out=null;
		try {
			out=response.getWriter();
			out.println(json);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null)
				out.close();
		}	
	}
	
}
