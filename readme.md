# Welcome LoMail！

简单，小众，隐私的的临时邮箱及实际/定向邮箱 XD

> ## **临时邮箱**

访问 https://outlook.com 登入以下信息：

用户名 login@loungemail.eu.org

密码 lounge123456

此刻，您就有获得了一个您接收邮箱的容器啦！

接下来，您可以自定义 name@loungemail.eu.org 邮件，**name为您的任意合法字母名称**，例如abcdefg@loungemail.eu.org 接下来，邮件就会在outlook账户中获取，您就会在账户中看到您的**接收电子邮件**了

也就是说，任何以loungemail.eu.org结尾的邮件都会发送至此outlook账户，因此，此outlook账户为公共账户，我们尽量使其账户稳定运行，如果更换容器用户名，我们会第一时间发布在本官网上，推荐您下载LoMail手机应用

> F&Q

Q.为什么不使用Get直接将邮件爬取在网站上？

A.在实际操作中，这将是一件很大的工程（对于服务器来说），我们推荐用户自建Python爬取邮件内容等，如果我们将此工程应用到exe和apk文件上，代码量会远远高于Python文件量，基于速度的安全性的要求，用户自建Python（如果有能力）是一个很好的选择

Q.我的个人信息会被泄露吗？

A.完全不会，甚至Outlook供应商也不会记录到您的个人信息，因为Lounges提供了双重定向


> ## 注册一个准Lounges账户

此功能暂时为内测功能，请联系reply@baiduplay.ml

> ## 定向账户

您可以使lounges.gq后缀的某一电子邮件地址发送到您的电子邮件上，您需要接收一个验证码，请留言register@lounges.gq

格式为：您的电子邮件+您要注册的电子邮件名称

> ## 无法定向（占用）邮件

```
login@lounges.gq
register@lounges.gq
```

> ## Python爬取代码

```
import imaplib , email , os
 
imapserver = 'outlook.office365.com'
emailuser = "bill@outlook.com"
emailpasswd = "billpasswd"
 
attachementdir=r"d:\a"  #附件存放的位置
 
conn = imaplib.IMAP4_SSL(imapserver)
conn.login(emailuser,emailpasswd)
 
conn.list()     #列出邮箱中所有的列表，如：收件箱、垃圾箱、草稿箱。。。
 
conn.select('INBOX')    #选择收件箱（默认）
 
result , dataid = conn.uid ( 'search' , None , "ALL" )
 
mailidlist = dataid[0].split ()       #转成标准列表,获得所有邮件的ID
 
# 解析邮件内容
def get_body(msg):
    if msg.is_multipart ():
        return get_body ( msg.get_payload ( 0 ) )
    else:
        return msg.get_payload ( None , decode=True )
     
#search('FROM','abc@outlook.com',conn)  根据输入的条件查找特定的邮件
def search(key,value,conn):
    result , data = conn.search(None,key,'"()"'.format(value))
    return data
 
#获取附件
def get_attachements(msg):
    for part in msg.walk():
        if part.get_content_maintype() == 'multipart':
            continue
        if part.get('Content-Disposition') is None:
            continue
        filename = part.get_filename()
 
        if bool(filename):
            filepath = os.path.join(attachementdir,filename)
            with open(filepath,'wb') as f:
                f.write(part.get_payload(decode=True))
 
 
for id in mailidlist:
    result , data = conn.fetch ( id , '(RFC822)' )  # 通过邮件id获取邮件
    e = email.message_from_bytes ( data[0][1] )
    subject = email.header.make_header ( email.header.decode_header ( e['SUBJECT'] ) )
    mail_from = email.header.make_header ( email.header.decode_header ( e['From'] ) )
    print("邮件的subject是%s" % subject)
    print("邮件的发件人是%s" % mail_from)
    body = str ( get_body ( e ) , encoding='utf-8' )    # utf-8 gb2312 GB18030解析中文日文英文
    print("邮件内容是%s" % body)
 
conn.logout()
```
> 守则

请勿进行违法操作，您的违规操作本站概不负责，如果您恶意删除或进行容器绑定等，后果将自负
