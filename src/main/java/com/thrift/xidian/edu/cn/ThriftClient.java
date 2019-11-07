package com.thrift.xidian.edu.cn;

import com.thrift.xidian.edu.cn.thrift.generated.Person;
import com.thrift.xidian.edu.cn.thrift.generated.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ThriftClient {

    public static void main(String[] args) {
        TTransport transport = new TFastFramedTransport(new TSocket("localhost", 3352), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {
            transport.open();
            Person person = client.getPersonByUsername("张三");
            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());

            Person person1 = new Person();
            person1.setMarried(true);
            person1.setAge(27);
            person1.setUsername("王五");
            client.savePerson(person1);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(), e);
        }finally {
            transport.close();
        }
    }
}
