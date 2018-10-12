import requests
import re
import time

for li in range(1,167):
    url = 'http://www.dytt8.net/html/gndy/dyzz/list_23_'+str(li)+'.html'
    html = requests.get(url)
    html.encoding = 'gb2312'
    movie_pages = re.findall(r'<a href="(.*?)" class="ulink">',html.text)
    for movie_page in movie_pages:
        time.sleep(0.5)
        real_movie_page = 'http://www.dytt8.net' + movie_page
        movie_page_html = requests.get(real_movie_page)
        movie_page_html.encoding = 'gb2312'
        download_address = re.findall(r'<a href="(.*?)">.*?</a></td>', movie_page_html.text)
        print(download_address)
        try:
            with open('xiaodianying.txt', 'a', encoding='utf-8') as file:
                file.write(download_address[0] + '\n')
        except:
            print('null')