import scrapy

from arana.items import DmozItem

class DmozSpider(scrapy.Spider):
    name = "dmoz"
    allowed_domains = ["www.nlm.nih.gov"]
    start_urls = [
        "https://www.nlm.nih.gov/medlineplus/druginfo/drug_Aa.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Ba.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Ca.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Da.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Ea.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Fa.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Ga.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Ha.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Ia.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Ja.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Ka.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_La.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Ma.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Na.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Oa.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Pa.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Qa.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Ra.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Sa.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Ta.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Ua.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Va.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Wa.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Xa.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Ya.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_Za.html",
		"https://www.nlm.nih.gov/medlineplus/druginfo/drug_00.html"
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
	