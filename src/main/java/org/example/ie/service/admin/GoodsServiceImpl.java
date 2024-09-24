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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, GoodsEntity> implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public ResponseResult<Map<String, Object>> getGoods(GoodsEntity goodsEntity) {
        if (goodsEntity.getCurrentPage() == null || goodsEntity.getCurrentPage() < 1)
            goodsEntity.setCurrentPage(1);

        IPage<GoodsEntity> iPage = new Page<>(goodsEntity.getCurrentPage(), 5);
        //构造条件,用&& ||来组合更多的条件
        LambdaQueryWrapper<GoodsEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (goodsEntity.getGname() != null) {
            lambdaQueryWrapper.like(GoodsEntity::getGname, goodsEntity.getGname());
        }
        if (goodsEntity.getGoodstypeId() != null) {
            lambdaQueryWrapper.eq(GoodsEntity::getGoodstypeId, goodsEntity.getGoodstypeId());
        }

        //分页查询
        IPage<GoodsEntity> page = page(iPage, lambdaQueryWrapper);
        Map<String, Object> myres = new HashMap<>();
        myres.put("allGoods", page.getRecords());
        myres.put("totalPage", page.getPages());
        return ResponseResult.getSuccessResult(myres);
    }

    @Override
    public ResponseResult<Map<String, String>> add(GoodsEntity goodsEntity) {
        byte[] myfile = goodsEntity.getLogoFile();
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
        goodsEntity.setGpicture(fileName);
        if ("add".equals(goodsEntity.getAct())) {
            boolean result = save(goodsEntity);
            if (result)//成功
                return ResponseResult.getMessageResult(null, "A001");
            else
                return ResponseResult.getMessageResult(null, "A002");
        } else {//修改
            boolean result = updateById(goodsEntity);
            if (result)
                return ResponseResult.getMessageResult(null, "A001");
            else
                return ResponseResult.getMessageResult(null, "A002");
        }
    }

    @Override
    public ResponseResult<Map<String, String>> delete(GoodsEntity goodsEntity) {
        // TODO: 判断是否可以删除
        //car!=null,type!=null,order!=null 不能删除
        if (goodsEntity.getId() != null) {
            boolean result = removeById(goodsEntity.getId());
            if (result)
                return ResponseResult.getMessageResult(null, "A001");
            else
                return ResponseResult.getMessageResult(null, "A002");
        }
        return ResponseResult.getMessageResult(null, "A002");
    }

    @Override
    public ResponseResult<List<Map<String, Object>>> myCartGoods(CartEntity cartEntity) {
        List<Map<String, Object>> maps = goodsMapper.myCartGoods(cartEntity.getBusertableId());
        return ResponseResult.getSuccessResult(maps);
    }

    @Override
    public ResponseResult<List<GoodsEntity>> getAdvGoods() {
        List<GoodsEntity> list = lambdaQuery().eq(GoodsEntity::getIsAdvertisement, 1)
                .orderByDesc(GoodsEntity::getId)
                .last("limit 5")
                .list();
        return ResponseResult.getSuccessResult(list);
    }

    @Override
    public ResponseResult<List<GoodsEntity>> getGoodsIndex(GoodsEntity goodsEntity) {
        LambdaQueryWrapper<GoodsEntity> lwrapper = new LambdaQueryWrapper<>();
        //条件构造
        if (goodsEntity.getGoodstypeId() != null && goodsEntity.getGoodstypeId() != 0) {
            lwrapper.eq(GoodsEntity::getGoodstypeId, goodsEntity.getGoodstypeId());
        }
        if (goodsEntity.getGname() != null && !goodsEntity.getGname().trim().isEmpty()) {
            lwrapper.like(GoodsEntity::getGname, goodsEntity.getGname());
        }

        lwrapper.orderByDesc(GoodsEntity::getId).last("limit 10");

        return ResponseResult.getSuccessResult(this.list(lwrapper));
    }

    @Override
    public ResponseResult<GoodsEntity> getGoodsById(GoodsEntity goodsEntity) {
        return ResponseResult.getSuccessResult(this.getById(goodsEntity.getId()));
    }
}
