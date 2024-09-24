package org.example.ie.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.ie.common.MyUtil;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.entity.CartEntity;
import org.example.ie.entity.GoodsEntity;
import org.example.ie.mapper.admin.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper,GoodsEntity> implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public ResponseResult<Map<String,Object>> getGoods(GoodsEntity goodsEntity) {
        LambdaQueryWrapper<GoodsEntity> queryWrapper = new LambdaQueryWrapper<>();

        if(goodsEntity.getGname() != null ){
            queryWrapper.eq(GoodsEntity::getGname,goodsEntity.getGname());
        }

        if(goodsEntity.getGoodstypeId() != null) {
            queryWrapper.eq(GoodsEntity::getGoodstypeId,goodsEntity.getGoodstypeId());
        }

        IPage<GoodsEntity> iPage = new Page<>(goodsEntity.getCurrentPage(), 5);

        IPage<GoodsEntity> page = page(iPage,queryWrapper);
        Map<String ,Object> myres = new HashMap<>();
        myres.put("allGoods",page.getRecords());
        myres.put("totalPage",page.getPages());
        return ResponseResult.getSuccessResult(myres);
    }

    @Override
    public ResponseResult<Map<String, Object>> add(GoodsEntity goodsEntity) {
        byte[] myfile = goodsEntity.getLogoFile();
        //如果选择了上传文件
        if (myfile != null && myfile.length > 0) {
            //这里的位置是前端Vue的静态资源路径，需要修改
            String path = "E:\\Vue_Project\\ebusiness-vue\\src\\assets";
            //获得上传文件原名
            String fileName = goodsEntity.getFileName();
            //对文件重命名
            String fileNewName = MyUtil.getNewFileName(fileName);
            File filePath = new File(path + File.separator + fileNewName);
            //如果文件目录不存在，创建目录
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            FileOutputStream out;
            try {
                out = new FileOutputStream(filePath);
                out.write(myfile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //将重命名后的图片名存到GoodsEntity对象中，添加到数据库时使用
            goodsEntity.setGpicture(fileNewName);
        }

        boolean b = this.save(goodsEntity);
        if (b) {
            return ResponseResult.getMessageResult(null, "A001");
        }
        return ResponseResult.getMessageResult(null, "A002");
    }

    @Override
    public ResponseResult<Map<String, Object>> del(GoodsEntity goodsEntity) {
        String path = "E:\\Vue_Project\\ebusiness-vue\\src\\assets" + File.separator + goodsEntity.getGname();
        File file = new File(path);
        if(file.exists()) {
            file.delete();
        }
        if(removeById(goodsEntity.getId()))
            return ResponseResult.getMessageResult(null, "A001");
        else
            return ResponseResult.getMessageResult(null, "A002");
    }

    @Override
    public ResponseResult<Map<String, Object>> fileInit(MultipartFile file) {

        return null;
    }

    @Override
    public ResponseResult<Map<String, Object>> update(GoodsEntity goodsEntity) {
        {
            byte[] myfile = goodsEntity.getLogoFile();
            //如果选择了上传文件
            if(myfile != null && myfile.length > 0) {
                //这里的位置是前端Vue的静态资源路径，需要修改
//            String path = "D:\\idea-workspace\\ebusiness-vue\\src\\assets";
                String path = "E:\\Vue_Project\\ebusiness-vue\\src\\assets";
                //获得上传文件原名
                String fileName = goodsEntity.getFileName();
                //对文件重命名
                String fileNewName = MyUtil.getNewFileName(fileName);
                File filePath = new File(path + File.separator + fileNewName);
                //如果文件目录不存在，创建目录
                if(!filePath.getParentFile().exists()) {
                    filePath.getParentFile().mkdirs();
                }
                FileOutputStream out;
                try {
                    out = new FileOutputStream(filePath);
                    out.write(myfile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //将重命名后的图片名存到GoodsEntity对象中，添加到数据库时使用
                goodsEntity.setGpicture(fileNewName);
            }
            boolean result;
            if("add".equals(goodsEntity.getAct())) {
                result = save(goodsEntity);
            }else {//修改
                result = updateById(goodsEntity);
            }
            if(result)//成功
                return ResponseResult.getMessageResult(null, "A001");
            else
                return ResponseResult.getMessageResult(null, "A002");
        }
    }

    @Override
    public ResponseResult<List<GoodsEntity>> getAdvGoods() {
        List<GoodsEntity> listAdv = lambdaQuery().eq(GoodsEntity::getIsAdvertisement, 1)
                .orderByDesc(GoodsEntity::getId)
                .last("limit 5")
                .list();
        return ResponseResult.getSuccessResult(listAdv);
    }

    @Override
    public ResponseResult<List<Map<String,Object>>> myCartGoods(CartEntity cartEntity) {
        List<Map<String,Object>> maps = goodsMapper.myCartGoods(cartEntity.getBusertableId());
        return ResponseResult.getSuccessResult(maps);
    }

    @Override
    public ResponseResult<List<GoodsEntity>> getGoodsIndex(GoodsEntity goodsEntity) {
        LambdaQueryWrapper<GoodsEntity> wrapper = new LambdaQueryWrapper<>();

        if(goodsEntity.getGname()!=null&&goodsEntity.getGoodstypeId()!=null){
            wrapper.eq(GoodsEntity::getGoodstypeId,goodsEntity.getGoodstypeId());
        }
        if(goodsEntity.getGname()!=null&& !goodsEntity.getGname().trim().isEmpty()){
            wrapper.like(GoodsEntity::getGname,goodsEntity.getGname());
        }

        wrapper.orderByDesc(GoodsEntity::getId).last("limit 10");
        return ResponseResult.getSuccessResult(list(wrapper));
    }

    @Override
    public ResponseResult<GoodsEntity> getGoodsById(GoodsEntity goodsEntity) {
        return ResponseResult.getSuccessResult(getById(goodsEntity.getId()));
    }
}
