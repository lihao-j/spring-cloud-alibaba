- 建立父工程，导入依赖  
- 进行idea热部署
- 构建生产者payment9001、payment9002
- 构建消费者order83
- 启动微服务，入驻nacos，访问测试：  
http://localhost:83/consumer/payment/nacos/11  
多次刷新，展现nacos天生负载均衡：轮询负载9001、9002


