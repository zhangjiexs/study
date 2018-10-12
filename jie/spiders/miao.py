import scrapy


class NgaSpider(scrapy.Spider):
    name = "NgaSpider"
    host = "https://blog.csdn.net"
    # start_urls是我们准备爬的初始页
    start_urls = [
        "https://blog.csdn.net/vample/article/details/80443889",
    ]

    # 这个是解析函数，如果不特别指明的话，scrapy抓回来的页面会由这个函数进行解析。
    # 对页面的处理和分析工作都在此进行，这个示例里我们只是简单地把页面内容打印出来。
    def parse(self, response):

        print(response.body)