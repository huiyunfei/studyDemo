package com.example.io;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Created by hui.yunfei@qq.com on 2019/9/4
 */
public class Nio {

    public static void main(String[] args) throws Exception{
        //打开一个serverSocketChannel
        //打开一个Selector
        //channel注册到select上，调用select函数阻塞
        //channel
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);
        selector.select();
        SocketChannel accept = channel.accept();
        int read = accept.read(byteBuffer);
        Set<SelectionKey> keys = selector.keys();

    }
}
