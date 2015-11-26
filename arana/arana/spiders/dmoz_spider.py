import scrapy

from arana.items import DmozItem

class DmozSpider(scrapy.Spider):
    name = "dmoz"
    allowed_domains = ["www.nlm.nih.gov"]
    start_urls = [
        "https://www.nlm.nih.gov/medlineplus",
    ]

    def parse(self, response):
		for href in response.css("a::attr('href')"):
			url = response.urljoin(href.extract())
			yield scrapy.Request(url, callback=self.parse_dir_contents)

    def parse_dir_contents(self, response):
		item = DmozItem()
		item['titulo'] = response.xpath('//title/text()').extract()
		item['texto'] = response.body
		yield item
	