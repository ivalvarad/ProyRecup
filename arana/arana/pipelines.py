# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: http://doc.scrapy.org/en/latest/topics/item-pipeline.html

import json

class AranaPipeline(object):	
	def process_item(self, item, spider):
		temp = str(item['titulo']).strip('[u\'').strip('\']').replace(':', '').replace(' ','')
		self.file = open('../paginas/' + temp + '.txt', 'wb')
		line = item['texto'] + "\n"
		self.file.write(line)
		return item
