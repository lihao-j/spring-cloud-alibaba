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
