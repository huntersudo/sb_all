//package com.project.seed.aop.log;
//
//import com.chinamobile.cmss.common.exception.ServerException;
//import com.chinamobile.cmss.common.util.MessageHelper;
//import com.chinamobile.cmss.dragnet.common.constant.LogItem;
//import com.chinamobile.cmss.dragnet.common.constant.MessageType;
//import com.chinamobile.cmss.dragnet.common.constant.StatusType;
//import com.chinamobile.cmss.dragnet.common.constant.WorkOrderType;
//import com.chinamobile.cmss.dragnet.common.dto.SysRoleDto;
//import com.chinamobile.cmss.dragnet.common.dto.SysUserDto;
//import com.chinamobile.cmss.dragnet.common.model.*;
//import com.chinamobile.cmss.dragnet.constant.DataType;
//import com.chinamobile.cmss.dragnet.dto.*;
//import com.chinamobile.cmss.dragnet.dto.message.OperationDto;
//import com.chinamobile.cmss.dragnet.dto.message.UpdateCommentDto;
//import com.chinamobile.cmss.dragnet.dto.workOrder.CreateWorkOrderDto;
//import com.chinamobile.cmss.dragnet.model.SysRole;
//import com.chinamobile.cmss.dragnet.model.SysUser;
//import com.chinamobile.cmss.dragnet.model.TblSysSms;
//import com.chinamobile.cmss.dragnet.persistence.CaseMapper;
//import com.chinamobile.cmss.dragnet.persistence.TblSysSmsMapper;
//import com.chinamobile.cmss.dragnet.persistence.TblWatchAreaMapper;
//import com.chinamobile.cmss.dragnet.service.*;
//import com.chinamobile.cmss.dragnet.util.SessionUtil;
//import org.apache.commons.lang.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Collection;
//
//import static com.chinamobile.cmss.dragnet.util.aop.AuditLogOperation.*;
//
///**
// * @author tujingwei
// * @date 2018/5/2 16:04
// */
//@Aspect
//@Component
//public class LogAspect {
//    private static Logger LOG = LoggerFactory.getLogger(LogAspect.class);
//
//    @Autowired
//    private SystemLogService systemLogService;
//
//    @Autowired
//    private WorkOrderService workOrderService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private RoleService roleService;
//
//    @Autowired
//    private EntourageAnalysisService entourageAnalysisService;
//
//    @Autowired
//    private MultiPhoneService multiPhoneService;
//
//    @Autowired
//    private FamilyMemberTaskService familyMemberTaskService;
//
//    @Autowired
//    private CircleAbnormalTaskService circleAbnormalTaskService;
//
//    @Autowired
//    private SingleCaseService singleCaseService;
//
//    @Autowired
//    private MultiCaseAssociationService multiCaseAssociationService;
//
//    @Autowired
//    private TraceToPersonTaskService traceToPersonTaskService;
//
//    @Autowired
//    private PersonInCaseService personInCaseService;
//
//    @Autowired
//    CaseMapper caseMapper;
//
//    @Autowired
//    TblWatchAreaMapper tblWatchAreaMapper;
//
//    @Autowired
//    TblSysSmsMapper tblSysSmsMapper;
//
//
//    @Pointcut("execution(* com.project.sb.web..*(..)) && @annotation(auditLog)")
//    public void logPointcut(AuditLog auditLog) {
//
//    }
//
//    @Around("logPointcut(auditLog)")
//    public Object writeLog(ProceedingJoinPoint pjp, AuditLog auditLog) throws Throwable {
//
//        Object[] params = pjp.getArgs();
//        AuditLogObject auditLogObject = auditLog.obj();
//        AuditLogOperation auditLogOperation = auditLog.op();
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        SysUserDto sysUserDto = getCurrentUserInfo(auditLogOperation, params, request);
//        TaskOperationDto taskOperationDto = null;
//        switch (auditLogObject) {
//            case SYSTEM:
//                taskOperationDto = initSystemOperation(auditLogOperation);
//                break;
//            case WORK_ORDER:
//                taskOperationDto = initWorkOrderOperation(auditLogOperation, params);
//                break;
//            case MESSAGE:
//                taskOperationDto = initMessageOperation(auditLogOperation, params);
//                break;
//            case MULTI_CASE_ASSOCIATION:
//                taskOperationDto = initMultCaseAssociationTaskOperation(params, auditLogOperation);
//                break;
//            case PATH_TRACK_PERSON:
//                taskOperationDto = initPathTracePersonTaskOperation(params, auditLogOperation);
//                break;
//            case ABNORMAL_SHUTDOWN:
//                taskOperationDto = addAbnormalShutdownTask(params, auditLogOperation);
//                break;
//            case CIRCLE_ABNORMAL:
//                taskOperationDto = initCircleAbnormalTaskOperation(params, auditLogOperation);
//                break;
//            case PERSON_IN_CASE:
//                taskOperationDto = initPersonInCaseTaskOperation(params, auditLogOperation);
//                break;
//            case FAMILY_MEMBER:
//                taskOperationDto = initFamilyMemberTaskOperation(params, auditLogOperation);
//                break;
//            case SINGLE_CASE:
//                taskOperationDto = initSingleCaseTaskOperation(params, auditLogOperation);
//                break;
//            case MULTI_PHONE:
//                taskOperationDto = initMultiPhoneTaskOperation(params, auditLogOperation);
//                break;
//            case ENTOURAGE:
//                taskOperationDto = initEntourageTaskOperation(params, auditLogOperation);
//                break;
//            case SHUTDOWN_SPACE_TIME:
//                taskOperationDto = addShutdownSpaceTimeTask(params, auditLogOperation);
//                break;
//            case TASK_LIST:
//                taskOperationDto = initTaskListOperation(auditLogOperation);
//                break;
//            case PERMISSION:
//                taskOperationDto = initRoleOperation(auditLogOperation, params);
//                break;
//            case CASE_MANAGE:
//                taskOperationDto = initCaseManageOperation(auditLogOperation, params);
//                break;
//            case LOCATION_SEARCH:
//                taskOperationDto = initLocationSearchOperation(params);
//                break;
//            case PERSON_IDENTITY:
//                taskOperationDto = initPersonIdentityOperation(auditLogOperation, params);
//                break;
//            case OBJECT_MARKER:
//                taskOperationDto = initObjectMarkerOperation(auditLogOperation, params);
//                break;
//            case DATA_MAINTENANCE:
//                taskOperationDto = initDataMaintenanceOperation(auditLogOperation, params);
//                break;
//            case PHONE_REGISTRATION:
//                taskOperationDto = initPhoneRegistrationOperation(auditLogOperation, params);
//                break;
//            case CELL_QUERY:
//                taskOperationDto = intiCellQueryOperation(auditLogOperation, params);
//                break;
//            case AREA_MANAGE:
//                taskOperationDto = initPersonalAreaOperation(auditLogOperation, params);
//                break;
//            case SMS_MANAGE:
//                taskOperationDto = initSmsManageOperation(auditLogOperation, params);
//                break;
//            case PERSON_CONTROL:
//                taskOperationDto = initPersonControl(auditLogOperation, params);
//                break;
//            case POLICE_CALL:
//                taskOperationDto = initPoliceCall(auditLogOperation, params);
//                break;
//            case EMERGENCY_COMMAND:
//                taskOperationDto = initEmergencyCommand(auditLogOperation,params);
//            default:
//        }
//        Object result = null;
//        try {
//            LOG.info("{}{}了{}", sysUserDto.getRealName(), auditLogOperation.val(), auditLogObject.val());
//            result = pjp.proceed();
//        } catch (Throwable throwable) {
//            throw throwable;
////            return result;
//        }
//        if (null == taskOperationDto) {
//            taskOperationDto = afterHandle(result, auditLogObject, auditLogOperation, params);
//        }
//        try {
////            systemLogService.insert(initTblSysLog(sysUserDto, auditLogObject.val(), taskOperationDto));
//            systemLogService.insert(sysUserDto,auditLogObject.val(),taskOperationDto);
//        } catch (Exception e) {
//            String message = MessageHelper.getMessage("MSG000032");
//            e.printStackTrace();
//            LOG.error(message, e.getMessage());
//        }
//        return result;
//    }
//
//    private TaskOperationDto initSmsManageOperation(AuditLogOperation auditLogOperation, Object[] params) {
//        TaskOperationDto taskOperationDto = new TaskOperationDto();
//        SmsTemplate smsTemplate;
//        switch (auditLogOperation) {
//            case ADD:
//                smsTemplate = matchObjectType(SmsTemplate.class, params);
//                taskOperationDto.setOperation(new StringBuilder("添加短信模板：").append(smsTemplate.getName()).toString());
//                break;
//            case UPDATE:
//                smsTemplate = matchObjectType(SmsTemplate.class, params);
//                taskOperationDto.setOperation(new StringBuilder("修改短信模板：").append(smsTemplate.getName()).toString());
//                break;
//            case DELETE:
//                String id = matchObjectType(String.class, params);
//                TblSysSms byId = tblSysSmsMapper.getById(id);
//                taskOperationDto.setOperation(new StringBuilder("删除短信模板：").append(byId.getName()).toString());
//                break;
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initPersonalAreaOperation(AuditLogOperation auditLogOperation, Object[] params) {
//        TaskOperationDto taskOperationDto = new TaskOperationDto();
//        Integer isPersonal;
//        switch (auditLogOperation) {
//            case DELETE:
//                String id = matchObjectType(String.class, params);
//                WatchArea watchArea = tblWatchAreaMapper.getWatchAreaByAreaId(id);
//                isPersonal = watchArea.getIsPersonalArea();
//                if (isPersonal == 1) {
//                    taskOperationDto.setOperation(new StringBuilder("删除个人区域：").append(watchArea.getName()).toString());
//                } else {
//                    taskOperationDto.setOperation(new StringBuilder("删除公共区域：").append(watchArea.getName()).toString());
//                }
//                break;
//            case ADD:
//                AreaParamDto areaParamDto = matchObjectType(AreaParamDto.class, params);
//                isPersonal = areaParamDto.getIsPersonalArea();
//                if (isPersonal == 1) {
//                    taskOperationDto.setOperation(new StringBuilder("新增个人区域：").append(areaParamDto.getName()).toString());
//                } else {
//                    taskOperationDto.setOperation(new StringBuilder("新增公共区域：").append(areaParamDto.getName()).toString());
//                }
//
//                break;
//            case UPDATE:
//                AreaParamDto areaParam = matchObjectType(AreaParamDto.class, params);
//                isPersonal = tblWatchAreaMapper.getWatchAreaByAreaId(areaParam.getId()).getIsPersonalArea();
//                if (isPersonal == 1) {
//                    taskOperationDto.setOperation(new StringBuilder("修改个人区域：").append(areaParam.getName()).toString());
//                } else {
//                    taskOperationDto.setOperation(new StringBuilder("修改公共区域：").append(areaParam.getName()).toString());
//                }
//                break;
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto intiCellQueryOperation(AuditLogOperation auditLogOperation, Object[] params) {
//        TaskOperationDto taskOperationDto = new TaskOperationDto();
//        switch (auditLogOperation) {
//            case NAME_QUERY:
//                String name = (String) params[0];
//                taskOperationDto.setOperation(new StringBuilder("根据名称查询基站，查询条件：").append(name).toString());
//                break;
//            case LAC_CELL_QUERY:
//                String lac = (String) params[0];
//                String cell = (String) params[1];
//                taskOperationDto.setOperation(new StringBuilder("根据lac/cell查询基站，查询条件：").append(lac).append("/").append(cell).toString());
//                break;
//            case LAT_LNG_QUERY:
//                Double lat = (Double) params[0];
//                Double lng = (Double) params[1];
//                taskOperationDto.setOperation(new StringBuilder("根据lat/lng查询基站，查询条件：").append(lat).append("/").append(lng).toString());
//                break;
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initPhoneRegistrationOperation(AuditLogOperation auditLogOperation, Object[] params) {
//        TaskOperationDto taskOperationDto = new TaskOperationDto();
//        String phone;
//        switch (auditLogOperation) {
//            case QUERY:
//                phone = matchObjectType(String.class, params);
//                taskOperationDto.setOperation(new StringBuilder("查看手机号：").append(phone).append(" 的归属地").toString());
//                break;
//            case EXPORT:
//                phone = matchObjectType(String.class, params);
//                taskOperationDto.setOperation(new StringBuilder("导出手机号：").append(phone).append(" 归属地查询结果").toString());
//                break;
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initDataMaintenanceOperation(AuditLogOperation auditLogOperation, Object[] params) {
//        TaskOperationDto taskOperationDto = new TaskOperationDto();
//        Integer type;
//        String fileName;
//        DataType dataType;
//        switch (auditLogOperation) {
//            case UPLOAD:
//                type = matchObjectType(Integer.class, params);
//                fileName = matchObjectType(MultipartFile.class, params).getOriginalFilename();
//                dataType = DataType.valueOf(type);
//                taskOperationDto.setOperation(new StringBuilder("维护").append(dataType.getName()).append("模块数据：").append(fileName).toString());
//                break;
//            case HISTORY:
//                type = matchObjectType(Integer.class, params);
//                dataType = DataType.valueOf(type);
//                taskOperationDto.setOperation(new StringBuilder("查看").append(dataType.getName()).append("模块的").append(auditLogOperation.val()).toString());
//                break;
//            case DOWNLOAD:
//                String taskId = matchObjectType(String.class, params);
//                taskOperationDto.setOperation(new StringBuilder("下载上传的维护数据，批次号：").append(taskId).toString());
//                break;
//            case DOWNLOAD_SEARCH:
//                type = matchObjectType(Integer.class, params);
//                dataType = DataType.valueOf(type);
//                taskOperationDto.setOperation(new StringBuilder("下载").append(dataType.getName()).append("模块的基础数据").toString());
//                break;
//            case DELETE:
//                type = matchObjectType(Integer.class, params);
//                dataType = DataType.valueOf(type);
//                taskOperationDto.setOperation(new StringBuilder("删除").append(dataType.getName()).append("模块的基础数据").toString());
//                break;
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initObjectMarkerOperation(AuditLogOperation auditLogOperation, Object[] params) {
//        TaskOperationDto taskOperationDto = new TaskOperationDto();
//        String fileName;
//        switch (auditLogOperation) {
//            case ADD:
//            case UPDATE:
//                ObjectMarkerDto objectMarkerDto = matchObjectType(ObjectMarkerDto.class, params);
//                taskOperationDto.setOperation(new StringBuilder(auditLogOperation.val())
//                        .append("物件标记：").append(objectMarkerDto.getName()).toString());
//                break;
//            case DELETE:
//                taskOperationDto.setOperation(new StringBuilder(auditLogOperation.val()).append("物件标记").toString());
//                break;
//            case IMPORT:
//                fileName = matchObjectType(MultipartFile.class, params).getOriginalFilename();
//                taskOperationDto.setOperation(new StringBuilder(auditLogOperation.val()).append("物件标记：").append(fileName).toString());
//                break;
//            case EXPORT:
//                MarkerObjectDownLoadDto markerObjectDownLoadDto = matchObjectType(MarkerObjectDownLoadDto.class, params);
//                taskOperationDto.setOperation(new StringBuilder(auditLogOperation.val()).append("物件标记，查找条件：").append(markerObjectDownLoadDto.getSearchKey()).toString());
//                break;
//            case ERROR_FILE:
//                fileName = matchObjectType(String.class, params);
//                taskOperationDto.setOperation(new StringBuilder("下载错误记录文件：").append(fileName).toString());
//                break;
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initPersonIdentityOperation(AuditLogOperation auditLogOperation, Object[] params) {
//        TaskOperationDto taskOperationDto = new TaskOperationDto();
//        switch (auditLogOperation) {
//            case QUERY:
//                PersonIdentityQueryDto personIdentityQueryDto = matchObjectType(PersonIdentityQueryDto.class, params);
//                StringBuilder stringBuilder = new StringBuilder()
//                        .append("查看")
//                        .append(personIdentityQueryDto.getTargetType().desc())
//                        .append(":")
//                        .append(personIdentityQueryDto.getTarget())
//                        .append(" 的实名制信息");
//                taskOperationDto.setOperation(stringBuilder.toString());
//                taskOperationDto.setDataType(WorkOrderType.IDENTITY_ACCESS.code());
//                taskOperationDto.setPhone(personIdentityQueryDto.getTarget());
//                break;
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private SysUserDto getCurrentUserInfo(AuditLogOperation auditLogOperation, Object[] params, HttpServletRequest request) {
//        SysUserDto sysUserDto = new SysUserDto();
//        if (auditLogOperation != AuditLogOperation.CODE && auditLogOperation != AuditLogOperation.LOGIN) {
//            sysUserDto = SessionUtil.getCurrentUser(request.getSession());
//            return sysUserDto;
//        }
//        String phone = "";
//        if (auditLogOperation == AuditLogOperation.CODE) {
//            phone = matchObjectType(LoginCodeDto.class, params).getPhone();
//        } else if (auditLogOperation == AuditLogOperation.LOGIN) {
//            phone = matchObjectType(LoginUserDto.class, params).getPhone();
//        } else if (auditLogOperation == AuditLogOperation.UPDATE_PASSWORD) {
//            phone = matchObjectType(PasswordModificationDto.class, params).getPhone();
//        }
//        if (!StringUtils.isEmpty(phone)) {
//            SysUser sysUser = userService.getUserByPhone(phone);
//            if (sysUser == null) {
//                String message = MessageHelper.getMessage("MSG000046", phone);
//                throw new ServerException(message);
//            }
//            sysUserDto.setIp(sysUser.getIp());
//            sysUserDto.setRealName(sysUser.getRealName());
//        }
//        return sysUserDto;
//    }
//
//    private TaskOperationDto initSystemOperation(AuditLogOperation auditLogOperation) {
//        TaskOperationDto taskOperationDto = new TaskOperationDto();
//        switch (auditLogOperation) {
//            case CODE:
//                taskOperationDto.setOperation("获取验证码");
//                break;
//            case LOGIN:
//                taskOperationDto.setOperation("登录系统");
//                break;
//            case LOGOUT:
//                taskOperationDto.setOperation("登出系统");
//                break;
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initLocationSearchOperation(Object[] params) {
//        TaskOperationDto taskOperationDto = new TaskOperationDto();
//        String phone = matchObjectType(PersonTraceQueryDto.class, params).getPhone();
//        taskOperationDto.setOperation(new StringBuilder("查看轨迹信息").toString());
//        taskOperationDto.setPhone(phone);
//        taskOperationDto.setDataType(WorkOrderType.TRACE_ACCESS.code());
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto addShutdownSpaceTimeTask(Object[] params, AuditLogOperation auditLogOperation) {
//        if (auditLogOperation != AuditLogOperation.ADD) {
//            return null;
//        }
//        String name = matchObjectType(ShutdownTimeSpaceTaskDto.class, params).getName();
//        TaskOperationDto taskOperationDto = new TaskOperationDto(new StringBuilder().append("新建关机时空分析任务：").append(name).toString());
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto addAbnormalShutdownTask(Object[] params, AuditLogOperation auditLogOperation) {
//        if (auditLogOperation != AuditLogOperation.ADD) {
//            return null;
//        }
//        String name = matchObjectType(AbnormalShutdownTaskDto.class, params).getName();
//        TaskOperationDto taskOperationDto = new TaskOperationDto(new StringBuilder().append("新建异常关机人员分析任务：").append(name).toString());
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initEntourageTaskOperation(Object[] params, AuditLogOperation auditLogOperation) {
//        TaskOperationDto taskOperationDto = null;
//        switch (auditLogOperation) {
//            case ADD:
//                String name = matchObjectType(EntourageTaskDto.class, params).getName();
//                taskOperationDto = new TaskOperationDto(new StringBuilder().append("新建随行人员分析任务：").append(name).toString());
//                break;
//            case TASK_RESULT:
//                taskOperationDto = new TaskOperationDto();
//                String taskId = matchObjectType(String.class, params);
//                EntourageTaskDto entourageTaskDto = entourageAnalysisService.getEntourageTaskById(taskId);
//                taskOperationDto.setTaskId(taskId);
//                taskOperationDto.setOperation(new StringBuilder("查看随行人员分析任务：").append(entourageTaskDto.getName()).append(" 的分析结果").toString());
//                break;
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initMultiPhoneTaskOperation(Object[] params, AuditLogOperation auditLogOperation) {
//        TaskOperationDto taskOperationDto = null;
//        switch (auditLogOperation) {
//            case ADD:
//                String name = matchObjectType(MultiPhoneTaskDto.class, params).getName();
//                taskOperationDto = new TaskOperationDto(new StringBuilder().append("新建多机多号分析任务：").append(name).toString());
//                break;
//            case TASK_RESULT:
//                String taskId = matchObjectType(String.class, params);
//                taskOperationDto = new TaskOperationDto();
//                MultiPhoneTaskDto multiPhoneTaskDto = multiPhoneService.getMultiPhoneTaskById(taskId);
//                taskOperationDto.setPhone(multiPhoneTaskDto.getAuditTargetValue());
//                taskOperationDto.setDataType(WorkOrderType.IDENTITY_ACCESS.code());
//                taskOperationDto.setOperation(new StringBuilder("查看多机多号分析任务：").append(multiPhoneTaskDto.getName()).append(" 的分析结果").toString());
//                break;
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initFamilyMemberTaskOperation(Object[] params, AuditLogOperation auditLogOperation) {
//        TaskOperationDto taskOperationDto = null;
//        switch (auditLogOperation) {
//            case ADD:
//                String name = matchObjectType(FamilyMemberTaskAddParam.class, params).getName();
//                taskOperationDto = new TaskOperationDto(new StringBuilder().append("新建家庭成员分析任务：").append(name).toString());
//                break;
//            case TASK_RESULT:
//                String taskId = matchObjectType(String.class, params);
//                taskOperationDto = new TaskOperationDto();
//                FamilyMemberTask familyMemberTask = familyMemberTaskService.getFamilyMemberTaskById(taskId);
//                taskOperationDto.setPhone(familyMemberTask.getAuditTargetValue());
//                taskOperationDto.setDataType(WorkOrderType.IDENTITY_ACCESS.code());
//                taskOperationDto.setOperation(new StringBuilder("查看家庭成员分析任务：").append(familyMemberTask.getName()).append(" 的分析结果").toString());
//                break;
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initCircleAbnormalTaskOperation(Object[] params, AuditLogOperation auditLogOperation) {
//        TaskOperationDto taskOperationDto = null;
//        switch (auditLogOperation) {
//            case ADD:
//                String name = matchObjectType(CircleAbnormalParamDto.class, params).getName();
//                taskOperationDto = new TaskOperationDto(new StringBuilder().append("新建圈选异常人员分析任务：").append(name).toString());
//                break;
//            case TASK_RESULT:
//                String taskId = matchObjectType(String.class, params);
//                CircleAbnormalTask circleAbnormalTask = circleAbnormalTaskService.getTaskById(taskId);
//                if (circleAbnormalTask != null) {
//                    taskOperationDto = new TaskOperationDto();
//                    taskOperationDto.setOperation(new StringBuilder("查看圈选异常人员分析任务：").append(circleAbnormalTask.getName()).append(" 的分析结果").toString());
//                }
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initSingleCaseTaskOperation(Object[] params, AuditLogOperation auditLogOperation) {
//        TaskOperationDto taskOperationDto = null;
//        switch (auditLogOperation) {
//            case ADD:
//                String name = matchObjectType(SingleCaseTaskDto.class, params).getName();
//                taskOperationDto = new TaskOperationDto(new StringBuilder().append("新建个案析疑分析任务：").append(name).toString());
//                break;
//            case TASK_RESULT:
//                String taskId = matchObjectType(SingleCaseResultQueryDto.class, params).getTaskId();
//                SingleCaseTask singleCaseTask = singleCaseService.getTask(taskId);
//                if (singleCaseTask != null) {
//                    taskOperationDto = new TaskOperationDto();
//                    taskOperationDto.setOperation(new StringBuilder("查看个案析疑分析任务：").append(singleCaseTask.getName()).append(" 的分析结果").toString());
//                }
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initMultCaseAssociationTaskOperation(Object[] params, AuditLogOperation auditLogOperation) {
//        TaskOperationDto taskOperationDto = null;
//        switch (auditLogOperation) {
//            case ADD:
//                String name = matchObjectType(MultiCaseAssociationTaskDto.class, params).getName();
//                taskOperationDto = new TaskOperationDto(new StringBuilder().append("新建多案时空碰撞分析任务：").append(name).toString());
//                break;
//            case TASK_RESULT:
//                String taskId = matchObjectType(String.class, params);
//                MultiCaseAssociationTaskDto multiCaseAssociationTaskDto = multiCaseAssociationService.getMultiCaseAssociationTaskById(taskId);
//                if (multiCaseAssociationTaskDto != null) {
//                    taskOperationDto = new TaskOperationDto(new StringBuilder("查看多案时空碰撞分析任务：").append(multiCaseAssociationTaskDto.getName()).append(" 的分析结果").toString());
//                }
//                break;
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initPathTracePersonTaskOperation(Object[] params, AuditLogOperation auditLogOperation) {
//        TaskOperationDto taskOperationDto = null;
//        switch (auditLogOperation) {
//            case ADD:
//                String name = matchObjectType(TraceToPersonTaskDto.class, params).getName();
//                taskOperationDto = new TaskOperationDto(new StringBuilder().append("新建已知轨迹查人分析任务：").append(name).toString());
//                break;
//            case TASK_RESULT:
//                String taskId = matchObjectType(String.class, params);
//                TraceToPersonTaskDto traceToPersonTaskDto = traceToPersonTaskService.getTask(taskId);
//                if (traceToPersonTaskDto != null) {
//                    taskOperationDto = new TaskOperationDto(new StringBuilder("查看已知轨迹查人分析任务：").append(traceToPersonTaskDto.getName()).append(" 的分析结果").toString());
//                }
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initPersonInCaseTaskOperation(Object[] params, AuditLogOperation auditLogOperation) {
//        TaskOperationDto taskOperationDto = null;
//        switch (auditLogOperation) {
//            case ADD:
//                String name = matchObjectType(PersonInCaseTaskDto.class, params).getName();
//                taskOperationDto = new TaskOperationDto(new StringBuilder().append("新建由人串案分析任务：").append(name).toString());
//                break;
//            case TASK_RESULT:
//                String taskId = matchObjectType(String.class, params);
//                PersonInCaseTaskDto personInCaseTask = personInCaseService.getPersonInCaseTaskById(taskId);
//                if (personInCaseTask != null) {
//                    taskOperationDto = new TaskOperationDto(new StringBuilder("查看由人串案分析任务：").append(personInCaseTask.getName()).append(" 的分析结果").toString());
//                }
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initTaskListOperation(AuditLogOperation auditLogOperation) {
//        StringBuilder stringBuilder = new StringBuilder();
//        switch (auditLogOperation) {
//            case QUERY:
//                stringBuilder.append("查看分析任务列表");
//                break;
//            case RETRY:
//                stringBuilder.append("重新执行了一个任务");
//                break;
//            default:
//        }
//        return new TaskOperationDto(stringBuilder.toString());
//    }
//
//    private TaskOperationDto initCaseManageOperation(AuditLogOperation auditLogOperation, Object[] params) {
//        StringBuilder stringBuilder = new StringBuilder();
//        switch (auditLogOperation) {
//            case QUERY:
//                stringBuilder.append("查看事件标记列表");
//                break;
//            case ADD:
//            case UPDATE:
//                String name = matchObjectType(CaseDto.class, params).getName();
//                if (auditLogOperation == AuditLogOperation.ADD) {
//                    stringBuilder.append("新增事件标记：").append(name);
//                } else if (auditLogOperation == AuditLogOperation.UPDATE) {
//                    stringBuilder.append("更新事件标记：").append(name);
//                }
//                break;
//            case DELETE:
//                String caseId = matchObjectType(String.class, params);
//                Case aCase = caseMapper.getCaseById(caseId);
//                stringBuilder.append("删除事件标记：").append(aCase.getName());
//                break;
//            default:
//        }
//        return new TaskOperationDto(stringBuilder.toString());
//    }
//
//    private TaskOperationDto initRoleOperation(AuditLogOperation auditLogOperation, Object[] params) {
//        StringBuilder stringBuilder = new StringBuilder();
//        switch (auditLogOperation) {
//            case QUERY:
//                stringBuilder.append("查看角色列表");
//                break;
//            case ADD:
//            case UPDATE:
//                String name = matchObjectType(SysRoleDto.class, params).getName();
//                if (auditLogOperation == AuditLogOperation.ADD) {
//                    stringBuilder.append("新建角色：").append(name);
//                } else {
//                    stringBuilder.append("编辑角色的权限：").append(name);
//                }
//                break;
//            case DELETE:
//                Integer roleId = matchObjectType(Integer.class, params);
//                SysRole sysRole = roleService.getRoleById(roleId);
//                stringBuilder.append("删除角色：").append(sysRole.getName());
//                break;
//            default:
//        }
//        return new TaskOperationDto(stringBuilder.toString());
//    }
//
//    private void initUserOperation(AuditLogOperation auditLogOperation, Object[] params, TaskOperationDto taskOperationDto) {
//        StringBuilder stringBuilder = new StringBuilder();
//        switch (auditLogOperation) {
//            case ADD:
//                String realName = matchObjectType(SysUserDto.class, params).getRealName();
//                stringBuilder.append("添加用户：").append(realName);
//                break;
//            case UPDATE:
//                String phone = matchObjectType(SysUserDto.class, params).getPhone();
//                stringBuilder.append("更新：").append(phone).append("的账号信息");
//                break;
//            case DISABLE:
//            case ENABLE:
//                Integer userId = (Integer) params[0];
//                final SysUser userById = userService.getUserById(userId);
//                if (auditLogOperation == AuditLogOperation.DISABLE) {
//                    stringBuilder.append("停用账号：").append(userById.getPhone());
//                } else {
//                    stringBuilder.append("启用账号：").append(userById.getPhone());
//                }
//                break;
//            case QUERY:
//                stringBuilder.append("查看账号列表");
//                break;
//            case UPDATE_PASSWORD:
//                stringBuilder.append("更新(自己)账号密码");
//                break;
//            case RESET_PASSWORD:
//                stringBuilder.append("重置密码");
//                break;
//            default:
//        }
//        taskOperationDto.setOperation(stringBuilder.toString());
//    }
//
//    private TaskOperationDto initWorkOrderOperation(AuditLogOperation auditLogOperation, Object[] params) {
//        TaskOperationDto taskOperationDto = new TaskOperationDto();
//        switch (auditLogOperation) {
//            case ADD:
//                CreateWorkOrderDto createWorkOrderDto = matchObjectType(CreateWorkOrderDto.class, params);
//                String workOrderName = createWorkOrderDto.getName();
//                taskOperationDto.setOperation(new StringBuilder("创建工单：").append(workOrderName).toString());
//                break;
//            case QUERY:
//                taskOperationDto.setOperation(new StringBuilder("查看我的工单列表").toString());
//                break;
//            case DETAIL:
//                String workOrderId = matchObjectType(String.class, params);
//                String name = workOrderService.getNameByworkOrderId(workOrderId);
//                taskOperationDto.setOperation(new StringBuilder("查看工单：").append(name).append(" 的详情").toString());
//                break;
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initMessageOperation(AuditLogOperation auditLogOperation, Object[] params) {
//        StringBuilder stringBuilder = new StringBuilder();
//        switch (auditLogOperation) {
//            case QUERY:
//                stringBuilder.append("查看消息列表");
//                break;
//            case OPERATION:
//                Integer result = matchObjectType(OperationDto.class, params).getResult();
//                if (result.intValue() == StatusType.AUDIT_PASSED.code().intValue()) {
//                    stringBuilder.append("通过审批");
//                } else if (result.intValue() == StatusType.AUDIT_FAILED.code().intValue()) {
//                    stringBuilder.append("拒绝审批");
//                } else if (result.intValue() == StatusType.SIGNED.code().intValue()) {
//                    stringBuilder.append("签收消息");
//                }
//                break;
//            case EXTRACT:
//                String seqCode = matchObjectType(String.class, params);
//                stringBuilder.append("提取消息，提取码：").append(seqCode);
//                break;
//            case UPDATE_COMMENT:
//                UpdateCommentDto updateCommentDto = matchObjectType(UpdateCommentDto.class, params);
//                MessageType messageType = MessageType.resolve(updateCommentDto.getType());
//                stringBuilder.append("修改 ").append(messageType.desc()).append("类 消息的备注");
//                break;
//            case STATISTICS:
//                stringBuilder.append("查看消息统计");
//                break;
//            default:
//        }
//        return new TaskOperationDto(stringBuilder.toString());
//    }
//
//    private void initShutDownSpaceTimeResultDto(Object object, AuditLogOperation auditLogOperation, TaskOperationDto taskOperationDto, Object[] objects) {
//        switch (auditLogOperation) {
//            case DETAIL:
//                if (object instanceof Collection) {
//                    Collection collection = (Collection) object;
//                    if (!collection.isEmpty()) {
//                        object = collection.iterator().next();
//                    }
//                }
//                if (object instanceof ShutdownTimeSpaceDetail) {
//                    ShutdownTimeSpaceDetail shutdownTimeSpaceDetail = (ShutdownTimeSpaceDetail) object;
//                    taskOperationDto.setPhone(shutdownTimeSpaceDetail.getPhone());
//                    taskOperationDto.setDataType(WorkOrderType.TRACE_ACCESS.code());
//                    taskOperationDto.setOperation("查看关机时空分析结果详情(关机地点)");
//                }
//                break;
//            case TASK_INFO:
//                ShutdownTimeSpaceTaskDto shutdownTimeSpaceTaskDto = (ShutdownTimeSpaceTaskDto) object;
//                String name = shutdownTimeSpaceTaskDto.getName();
//                taskOperationDto.setTaskName(name);
//                taskOperationDto.setDataType(WorkOrderType.IDENTITY_ACCESS.code());
//                taskOperationDto.setPhone(shutdownTimeSpaceTaskDto.getAuditTarget());
//                taskOperationDto.setOperation("查看关机时空分析任务：" + name + " 详情");
//                taskOperationDto.setTaskId((String) objects[0]);
//                break;
//            default:
//        }
//    }
//
//    private TaskOperationDto afterHandle(Object object, AuditLogObject auditLogObject, AuditLogOperation auditLogOperation, Object[] objects) {
//        TaskOperationDto taskOperationDto = new TaskOperationDto();
//        switch (auditLogObject) {
//            case USER:
//                initUserOperation(auditLogOperation, objects, taskOperationDto);
//                break;
//            case SHUTDOWN_SPACE_TIME:
//                initShutDownSpaceTimeResultDto(object, auditLogOperation, taskOperationDto, objects);
//                break;
//            case ABNORMAL_SHUTDOWN:
//                initAbnormalShutdownResultDto(object, auditLogOperation, taskOperationDto, objects);
//                break;
//            case CIRCLE_ABNORMAL:
//                initCircleAbnormalResultDto(object, auditLogOperation, taskOperationDto, objects);
//                break;
//            case SINGLE_CASE:
//                initSingleCaseResultDto(object, auditLogOperation, taskOperationDto, objects);
//                break;
//            case PATH_TRACK_PERSON:
//                initTraceToPathResultDto(object, auditLogOperation, taskOperationDto, objects);
//                break;
//            default:
//
//        }
//        return taskOperationDto;
//    }
//
//    private void initTraceToPathResultDto(Object object, AuditLogOperation auditLogOperation, TaskOperationDto taskOperationDto, Object[] objects) {
//        switch (auditLogOperation) {
//            case IDENTITY_ACCESS:
//                PhoneIdentityDto phoneIdentityDto = (PhoneIdentityDto) object;
//                if (phoneIdentityDto != null) {
//                    taskOperationDto.setPhone(phoneIdentityDto.getPhone());
//                    taskOperationDto.setDataType(WorkOrderType.IDENTITY_ACCESS.code());
//                    taskOperationDto.setOperation("查看实名制资料");
//                }
//                break;
//            case TRACE_ACCESS:
//                TraceToPersonDto traceToPersonDto = (TraceToPersonDto) object;
//                if (traceToPersonDto != null) {
//                    taskOperationDto.setPhone(traceToPersonDto.getPhone());
//                    taskOperationDto.setDataType(WorkOrderType.TRACE_ACCESS.code());
//                    taskOperationDto.setOperation("查看个人轨迹");
//                }
//                break;
//            case TASK_INFO:
//                if (object != null) {
//                    TraceToPersonTaskDto traceToPersonTaskDto = (TraceToPersonTaskDto) object;
//                    String name = traceToPersonTaskDto.getName();
//                    taskOperationDto.setOperation(new StringBuilder("查看已知轨迹查人分析任务：").append(name).append(" 的任务详情").toString());
//                    taskOperationDto.setTaskId((String) objects[0]);
//                }
//            default:
//        }
//    }
//
//    private void initSingleCaseResultDto(Object object, AuditLogOperation auditLogOperation, TaskOperationDto taskOperationDto, Object[] objects) {
//        switch (auditLogOperation) {
//            case TASK_INFO:
//                if (object != null) {
//                    SingleCaseTask singleCaseTask = (SingleCaseTask) object;
//                    String name = singleCaseTask.getName();
//                    taskOperationDto.setOperation(new StringBuilder("查看个案析疑分析任务：").append(name).append(" 的任务详情").toString());
//                    taskOperationDto.setTaskId((String) objects[0]);
//                }
//                break;
//            default:
//        }
//    }
//
//    private void initAbnormalShutdownResultDto(Object object, AuditLogOperation auditLogOperation, TaskOperationDto taskOperationDto, Object[] objects) {
//        switch (auditLogOperation) {
//            case DETAIL:
//                if (object != null) {
//                    AbnormalShutdownDetailDto abnormalShutdownDetailDto = (AbnormalShutdownDetailDto) object;
//                    taskOperationDto.setPhone(abnormalShutdownDetailDto.getPhone());
//                    taskOperationDto.setDataType(WorkOrderType.TRACE_ACCESS.code());
//                    taskOperationDto.setOperation("查看异常关机人员分析任务结果详情(开关机地点)");
//                }
//                break;
//            case TASK_INFO:
//                AbnormalShutdownTaskDto abnormalShutdownTaskDto = (AbnormalShutdownTaskDto) object;
//                String name = abnormalShutdownTaskDto.getName();
//                taskOperationDto.setTaskName(name);
//                taskOperationDto.setOperation("查看异常关机人员分析任务：" + name + " 详情");
//                taskOperationDto.setTaskId((String) objects[0]);
//                break;
//            default:
//        }
//    }
//
//    private void initCircleAbnormalResultDto(Object object, AuditLogOperation auditLogOperation, TaskOperationDto taskOperationDto, Object[] objects) {
//        switch (auditLogOperation) {
//            case TASK_INFO:
//                if (object != null) {
//                    CircleAbnormalDisplayDto circleAbnormalDisplayDto = (CircleAbnormalDisplayDto) object;
//                    String name = circleAbnormalDisplayDto.getName();
//                    taskOperationDto.setOperation(new StringBuilder("查看圈选异常人员分析任务：").append(name).append(" 的任务详情").toString());
//                    taskOperationDto.setTaskId((String) objects[0]);
//                }
//                break;
//            default:
//        }
//    }
//
//    private TaskOperationDto initPersonControl(AuditLogOperation auditLogOperation, Object[] params) {
//        TaskOperationDto taskOperationDto = null;
//
//        switch (auditLogOperation) {
//            case MARK_ENABLE:
//                taskOperationDto = new TaskOperationDto();
//                String phone = (String) params[0];
//                taskOperationDto.setPhone(phone);
//                taskOperationDto.setOperation(MARK_ENABLE.val());
//                break;
//            case MARK_DISABLE:
//                taskOperationDto = new TaskOperationDto();
//                String phone1 = (String) params[0];
//                taskOperationDto.setPhone(phone1);
//                taskOperationDto.setOperation(MARK_DISABLE.val());
//                break;
//            case TRACE_QUERY:
//                taskOperationDto = new TaskOperationDto();
//                String phone2 = (String) params[0];
//                taskOperationDto.setPhone(phone2);
//                taskOperationDto.setOperation(TRACE_QUERY.val());
//                break;
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initPoliceCall(AuditLogOperation auditLogOperation, Object[] params) {
//        TaskOperationDto taskOperationDto = null;
//
//        switch (auditLogOperation) {
//            case FAVORITE_DISABLE:
//                taskOperationDto = new TaskOperationDto();
//                String phone = (String) params[0];
//                taskOperationDto.setPhone(phone);
//                taskOperationDto.setOperation(FAVORITE_DISABLE.val());
//                break;
//            case FAVORITE_ENABLE:
//                taskOperationDto = new TaskOperationDto();
//                String phone1 = (String) params[0];
//                taskOperationDto.setPhone(phone1);
//                taskOperationDto.setOperation(FAVORITE_ENABLE.val());
//                break;
//            case TRACE_QUERY:
//                taskOperationDto = new TaskOperationDto();
//                String phone2 = (String) params[0];
//                taskOperationDto.setPhone(phone2);
//                taskOperationDto.setOperation(TRACE_QUERY.val());
//                break;
//            case SEND_MESSAGE:
//            case LOCATION_PUSH:
//                taskOperationDto = new TaskOperationDto();
//                if(params[0] instanceof InformDto){
//                    InformDto informDto = (InformDto) params[0];
//                    taskOperationDto.setOperation(auditLogOperation.val());
//                    taskOperationDto.setItemKey(LogItem.SMS_CONTENT.itemKey());
//                    taskOperationDto.setItemValue(informDto.getMessage());
//                }
//                break;
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private TaskOperationDto initEmergencyCommand(AuditLogOperation auditLogOperation, Object[] params) {
//        TaskOperationDto taskOperationDto = new TaskOperationDto();
//        switch (auditLogOperation){
//            case POLICE_SCHEDULE:
//            case POLICE_WARN:
//                taskOperationDto = new TaskOperationDto();
//                taskOperationDto.setOperation(auditLogOperation.val());
//                taskOperationDto.setItemKey(LogItem.SEND_COUNT.itemKey());
//                Integer count = (int)(Math.random()*100 + 1);
//                taskOperationDto.setItemValue(String.valueOf(count));
//                break;
//            default:
//        }
//        return taskOperationDto;
//    }
//
//    private static <T> T matchObjectType(Class<T> clazz, Object[] params) {
//        for (Object object : params) {
//            if (clazz.isInstance(object)) {
//                return clazz.cast(object);
//            }
//        }
//        return null;
//    }
//}