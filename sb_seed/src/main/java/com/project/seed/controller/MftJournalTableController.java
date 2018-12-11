package com.project.seed.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.seed.aop.log.ModuleConstant;
import com.project.seed.generator.Result;
import com.project.seed.generator.ResultGenerator;
import com.project.seed.model.MftJournalTable;
import com.project.seed.service.MftJournalTableService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* @author author on 2018/12/11.
*/
@RestController
@RequestMapping("/mft/journal/table")
@Api(value = "",tags = ModuleConstant.MFT_MODULE)

public class MftJournalTableController {
    @Resource
    private MftJournalTableService mftJournalTableService;

    @PostMapping
    public Result add(@RequestBody MftJournalTable mftJournalTable) {
        mftJournalTableService.save(mftJournalTable);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        mftJournalTableService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody MftJournalTable mftJournalTable) {
        mftJournalTableService.update(mftJournalTable);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        MftJournalTable mftJournalTable = mftJournalTableService.findById(id);
        return ResultGenerator.genSuccessResult(mftJournalTable);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<MftJournalTable> list = mftJournalTableService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
