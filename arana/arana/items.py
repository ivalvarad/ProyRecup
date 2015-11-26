import scrapy

class DmozItem(scrapy.Item):
	titulo = scrapy.Field()
	texto = scrapy.Field()