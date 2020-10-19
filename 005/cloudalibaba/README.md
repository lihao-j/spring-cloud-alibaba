- 建立父工程，导入依赖  
- 进行idea热部署
- 构建生产者payment9001、payment9002
- 构建消费者order83
- 启动微服务，入驻nacos，访问测试：  
http://localhost:83/consumer/payment/nacos/11  
多次刷新，展现nacos天生负载均衡：轮询负载9001、9002

-----------------------------------------
### Nacos作为配置中心-基础配置
- 建立client3377配置module   
建立ConfigClientController业务类，用@Value("${config.info}")来获取config.info，注意添加@RefreshScope，它支持Nacos的动态刷新功能
- Nacos界面进行配置
- 运行cloud-config-nacos-client3377的主启动类
- http://localhost:3377/config/info
- 再次修改下Nacos中的yaml配置文件，再次调用查看配置的接口，就会发现配置已经刷新
-----------------------------------------
### Nacos作为配置中心-分类配置
- Namespace
- Group
- Data
-----------------------------------------
### 初始化演示工程
- 搭建service的module，入驻sentinel
- 启动Sentinel8080
- 启动微服务8401
- 注意Sentinel采用的懒加载，先访问:
http://localhost:8401/testA、http://localhost:8401/testB
才能看到sentinel8080正在监控微服务8401
### 流控模式测试
- 测试流控直接模式，在控制台设置阈值类型：QBS为1
- 测试流控直接模式，在控制台设置阈值类型：线程数为1
- 测试流控关联模式，关联testA和testB，重新在设置testA阈值类型：QBS为1，postman模拟并发密集访问testB,大批量线程高并发访问B，导致A失效了
- 测试流控链路模式，将testA的入口资源设置为sentinel_web_servlet_context
### 流控效果测试
- 切换testB进行流控效果：Warmup配置，测试预热，多次点击:
http://localhost:8401/testB，  
刚开始不行，后续慢慢OK
- 进行流控效果：排队等待设置，postman模拟并发密集访问testB,大批量线程高并发访问B。

搭建微服务8401，在此基础上进行初始化演示工程、流控模式测试、流控效果测试

-----------------------------------------
### 降级规则
- 测试RT（jmeter压测）
- 测试异常比例（jmeter压测）
- 测试异常数

-----------------------------------------
### 热点key限流
- 对请求中的某个参数设置降级策略，用到@SentinelResource注解，value的值和控制台中的资源名对应，blockHandler的值默认是空（直接报500错），用来对应自定义的兜底方法名。兜底方法中参数必须和路径方法参数对应，同时还需要加一个BlockException类型参数，控制台中参数索引从0开始
- 参数例外项

-----------------------------------------
## @SentinelResource
- 按资源名称限流+后续处理
- 按照Url地址限流+后续处理
- 客户自定义限流处理逻辑