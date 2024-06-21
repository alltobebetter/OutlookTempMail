import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GmailReaderExample {

    public static void main(String[] args) {
        // Gmail IMAP 设置
        String host = "imap.gmail.com";
        String port = "993";

        // 你的 Gmail 账户信息
        String username = "your.gmail.username@gmail.com"; 
        String password = "your.gmail.password";

        try {
            Properties props = new Properties();
            props.put("mail.store.protocol", "imaps");
            props.put("mail.imaps.host", host);
            props.put("mail.imaps.port", port);
            props.put("mail.imaps.ssl.enable", "true");

            Session session = Session.getInstance(props);
            Store store = session.getStore("imaps");
            store.connect(host, username, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                System.out.println("主题: " + message.getSubject());
                System.out.println("发件人: " + message.getFrom()[0]);
                // ... 处理其他邮件属性 ...
            }

            inbox.close(false);
            store.close();

        } catch (MessagingException e) {
            System.err.println("发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
