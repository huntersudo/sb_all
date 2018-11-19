package com.project.sb.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.sb.aop.wrapper.Result;
import com.project.sb.aop.wrapper.ResultGenerator;
import com.project.sb.model.AuditTable;
import com.project.sb.service.AuditTableService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CodeGenerator on 2018/10/25.
 */
@RestController
@RequestMapping("/audit/table")
@Api(value = "审计表模块", tags = "user_module")
public class AuditTableController {
    @Resource
    private AuditTableService auditTableService;

    @PostMapping
    public Result add(@RequestBody AuditTable auditTable) {
        auditTableService.save(auditTable);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        auditTableService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody AuditTable auditTable) {
        auditTableService.update(auditTable);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        AuditTable auditTable = auditTableService.findById(id);
        return ResultGenerator.genSuccessResult(auditTable);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<AuditTable> list = auditTableService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
