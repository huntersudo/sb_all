package com.project.seed.controller;

import com.project.seed.generator.Result;
import com.project.seed.generator.ResultGenerator;
import com.project.seed.model.AuditTable;
import com.project.seed.service.AuditTableService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* @author author on 2018/12/10.
*/
@RestController
@RequestMapping("/audit/table")
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
