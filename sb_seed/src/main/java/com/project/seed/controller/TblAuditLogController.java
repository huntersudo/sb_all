package com.project.seed.controller;

import com.project.seed.aop.log.ModuleConstant;
import com.project.seed.generator.Result;
import com.project.seed.generator.ResultGenerator;
import com.project.seed.model.TblAuditLog;
import com.project.seed.service.TblAuditLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* @author author on 2018/12/11.
*/
@RestController
@RequestMapping("/tbl/audit/log")
@Api(value = "",tags = ModuleConstant.AUDIT_LOG_MODULE)

public class TblAuditLogController {
    @Resource
    private TblAuditLogService tblAuditLogService;

    @PostMapping
    public Result add(@RequestBody TblAuditLog tblAuditLog) {
        tblAuditLogService.save(tblAuditLog);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        tblAuditLogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody TblAuditLog tblAuditLog) {
        tblAuditLogService.update(tblAuditLog);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        TblAuditLog tblAuditLog = tblAuditLogService.findById(id);
        return ResultGenerator.genSuccessResult(tblAuditLog);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TblAuditLog> list = tblAuditLogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
