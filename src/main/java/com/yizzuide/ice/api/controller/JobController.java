package com.yizzuide.ice.api.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.github.yizzuide.milkomeda.comet.core.CometParam;
import com.github.yizzuide.milkomeda.hydrogen.uniform.ResultVO;
import com.github.yizzuide.milkomeda.hydrogen.uniform.UniformPage;
import com.github.yizzuide.milkomeda.hydrogen.uniform.UniformQueryPageData;
import com.github.yizzuide.milkomeda.hydrogen.uniform.UniformResult;
import com.github.yizzuide.milkomeda.ice.Ice;
import com.github.yizzuide.milkomeda.ice.Job;
import com.github.yizzuide.milkomeda.ice.inspector.JobStatInfo;
import com.github.yizzuide.milkomeda.ice.inspector.JobWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * JobController
 *
 * @author yizzuide
 * <br />
 * Create at 2022/10/07 20:30
 */
@Api(tags = "Job API")
@ApiSupport(author = "yizzuide")
@RestController
@RequestMapping("job")
public class JobController {

    @Autowired
    private Ice ice;

    /**
     * 添加Job延迟任务
     * @param job 必需设置属性：id（唯一id), topic（消费Topic标识）, delay（延迟ms), body（JSON业务数据）
     */
    @ApiOperation("添加job延迟")
    @ApiImplicitParam(name = "job", value = "job信息对象", required = true)
    @PreAuthorize("@crust.permitAny('service:task:ice:push')")
    @PostMapping("push")
    public ResultVO<?> pushJob(@RequestBody Job<?> job) {
        ice.add(job);
        return UniformResult.ok(null);
    }

    /**
     * 获取job探针数据
     *  @param queryPageData page data for query
     * @return  job inspection data
     */
    @PreAuthorize("@crust.permitAny('service:task:ice:select')")
    @GetMapping("jobInspectInfos")
    public ResultVO<UniformPage<JobWrapper>> getJobPage(@CometParam UniformQueryPageData<JobWrapper> queryPageData) {
        return UniformResult.ok(ice.getJobInspectPage(queryPageData));
    }

    /**
     * 获取当前job的详情信息
     * @param jobId job id
     * @param topic job topic
     * @return  job
     */
    @PreAuthorize("@crust.permitAny('service:task:ice:detail')")
    @GetMapping("jobDetail")
    public ResultVO<Job<?>> jobDetail(String jobId, String topic) {
        return UniformResult.ok(ice.getJobDetail(jobId, topic));
    }

    /**
     * 获取缓存key
     * @param jobId job id
     * @param topic job topic
     * @return  cache key map
     */
    @GetMapping("jobCacheKeys")
    public ResultVO<Map<String, String>> jobCacheKeys(String jobId, String topic) {
        return UniformResult.ok(ice.getCacheKey(jobId, topic));
    }

    /**
     * 重推Job
     * @param jobId job id
     * @param topic job topic
     * @return null
     */
    @PreAuthorize("@crust.permitAny('service:task:ice:rePush')")
    @PutMapping("rePush")
    public ResultVO<?> rePushJob(String jobId, String topic) {
        boolean isSuccess = ice.rePushJob(jobId, topic);
        return isSuccess ? UniformResult.ok(null) : UniformResult.error("23", "Job当前状态不需要重新推送！");
    }

    /**
     * 获取job统计数据
     * @return JobStatInfo
     */
    @GetMapping("jobStat")
    public ResultVO<JobStatInfo> jobStat() {
        return UniformResult.ok(ice.getStatInfo());
    }

    /**
     * 拉取可消费Job
     * @param topic 消费Topic标识
     * @param count 获取最多消费个数
     * @return job list
     */
    @GetMapping("pull")
    public ResultVO<List<Job<Map<String, Object>>>> pull(String topic, Integer count) {
        return UniformResult.ok(ice.pull(topic, count));
    }
}
