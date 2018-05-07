# krry_ai_msgShop
识别天猫店铺营业执照的文字信息并写入Excel

# 天猫店铺工商信息的提取
> 中软杯项目

## 技术栈
- 前端 html5 + css + javaScript + jQuery
- 后台 java
- 接口 百度AI

- 识别的图片类型，图片要有背景
- 赛事官方给出的样本是无背景的文字图片，后台需要转化
- 接口要求 api key、 api secret，获取access token
- 需要将图片base64编码后再进行urlencode
- 请求参数：image（图像数据，base64编码后进行urlencode），detect_direction（检测图像朝向）
- 返回数据：
log_id	唯一的log id，用于问题定位<br>
words_result_num	识别结果数，表示words_result的元素个数<br>
words_result	定位和识别结果数组<br>
location	位置数组（坐标0点为左上角）<br>
left	表示定位位置的长方形左上顶点的水平坐标<br>
top	表示定位位置的长方形左上顶点的垂直坐标<br>
width	表示定位位置的长方形的宽度<br>
height	表示定位位置的长方形的高度<br>
words	识别结果字符串<br>
chars	单字符结果，recognize_granularity=small时存在<br>
location	位置数组（坐标0点为左上角）<br>
left	表示定位位置的长方形左上顶点的水平坐标<br>
top	表示定位位置的长方形左上顶点的垂直坐标<br>
width	表示定位定位位置的长方形的宽度<br>
height	表示位置的长方形的高度<br>
char	单字符识别结果<br>
probability	识别结果中每一行的置信度值，包含average：行置信度平均值，variance：行置信度方差，min：行置信度最小值<br>

- 所需要的字段：words_result
- 转化成json对象和数组进行解析和提取数据
- 根据路径中文件夹内，循环顺序扫描读取
- 存进list集合，写入Excel文件中

## 项目地址：https://www.ainyi.com/krry_ai_msgShop


