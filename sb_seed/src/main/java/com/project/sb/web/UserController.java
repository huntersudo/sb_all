package com.project.sb.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.sb.aop.wrapper.Result;
import com.project.sb.aop.wrapper.ResultGenerator;
import com.project.sb.dto.UserDto;
import com.project.sb.model.User;
import com.project.sb.service.UserService;
import com.project.common.dto.converter.DtoConverterHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/10/25.
*/
@RestController
@RequestMapping("/user")
//@Api(value="说明", tags="Swagger类别")

@Api(value = "User",tags="user_module")
public class UserController {

    @Autowired
    private DtoConverterHandler dtoConverterHandler;

    @Resource
    private UserService userService;

    /**
     * tags跳出所属类的tags,重新分类
     * @param userDto
     * @return
     */
    @ApiOperation(value="添加",notes = "添加User" )
    @PostMapping
    public Result add(@RequestBody UserDto userDto) {

        User user=(User) dtoConverterHandler.convertSingle(userDto,User.class);

        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="更新")
    @PutMapping
    public Result update(@RequestBody User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        User user = userService.findById(id);

        //model 2 dto
        return ResultGenerator.genSuccessResult(dtoConverterHandler.convertSingle(user,UserDto.class));
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
