# 欢迎来到OutlookTempMail

**再次提醒：login@loungemail.eu.org，admin@loungemail.eu.org，su@loungemail.eu.org这几个特殊邮件地址是无法接收邮件的！**

简单，小众，隐私的的临时邮箱。基于Outlook作为容器。

如果您有更多需求，可以联系我：alltobebetter@outlook.com

## 登入临时邮箱

访问 https://outlook.com 登入以下信息：

用户名 login@loungemail.eu.org

密码 lounge123456

此刻，您就有获得了一个您接收邮箱的容器啦！

## 接收邮件

接下来，您可以**自定义** `xxx@loungemail.eu.org` 邮件，**xxx为任意值**，例如`abcdefg@loungemail.eu.org` 接下来，邮件就会在此outlook账户中获取，您就会在账户中看到您的**邮件**了！

也就是说，任何以loungemail.eu.org结尾的邮件都会发送至此outlook账户，因此，此outlook账户为公共账户，我们尽量使其账户稳定运行。

## 发送邮件

尽管发送邮件不完全自由，但是我们为您绑定了几个邮件服务名称：

login@loungemail.eu.org

butimhere@outlook.com

howusay@outlook.com

publicmail@loungemail.eu.org

thisislounge@outlook.com

在发件时，您可以使用这些别名来发件。

## 无法接收邮件的邮箱

这些邮箱包括个人邮箱（已经被注册的）和官方邮箱

login@loungemail.eu.org（但是您可以用来发件）

admin@loungemail.eu.org

su@loungemail.eu.org

## 问题合集

> Q.为什么不使用Get直接将邮件爬取在网站上？

A.在实际操作中，这将是一件很大的工程（对于服务器来说），我们推荐用户自建Python爬取邮件内容等，如果我们将此工程应用到exe和apk文件上，代码量会远远高于Python文件量，基于速度的安全性的要求，用户自建Python（如果有能力）是一个很好的选择。

> Q.我的个人信息会被泄露吗？

A.完全不会，甚至Outlook供应商也不会记录到您的个人信息，因为邮箱提供了双重定向，即您的账户受到Loungemail以及您自身的双重保护，没有人知道您是谁。

## 注册一个私人账户

即创建一个别人无法使用的账户仅为您所用，请联系admin@loungemail.eu.org

## Python爬取代码

```
import imaplib , email , os
 
imapserver = 'outlook.office365.com'
emailuser = "login@loungemail.eu.org"
emailpasswd = "lounge123456"
 
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

## 守则

请勿进行违法操作，您的违规操作本站概不负责，如果您恶意删除或进行容器绑定等，后果将自负。
