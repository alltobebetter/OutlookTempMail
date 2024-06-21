package main

import (
    "context"
    "fmt"
    "log"
    "os"

    "github.com/microsoftgraph/msgraph-sdk-go/models"
    "github.com/microsoftgraph/msgraph-sdk-go/users"
    "github.com/microsoftgraph/msgraph-sdk-go/users/item/messages"

    graph "github.com/microsoftgraph/msgraph-sdk-go"
    "github.com/microsoftgraph/msgraph-sdk-go/auth"
)

func main() {
    clientId := "你的应用程序ID"
    tenantId := "你的租户ID"
    clientSecret := "你的应用程序机密"

    // 创建身份验证提供程序
    cred, err := auth.NewClientSecretProvider(tenantId, clientId, clientSecret, []string{"https://graph.microsoft.com/.default"})
    if err != nil {
        log.Fatalf("创建身份验证提供程序时出错: %v", err)
    }

    // 创建 Graph 客户端
    client := graph.NewGraphClientWithCredentials(cred, nil)

    // 获取用户邮件
    result, err := client.Users().ByUserId("用户ID").Messages().Request().Get(context.Background())
    if err != nil {
        log.Fatalf("获取邮件时出错: %v", err)
    }

    // 打印邮件主题
    for _, message := range result.GetValue() {
        fmt.Println(*message.GetSubject())
    }
}
