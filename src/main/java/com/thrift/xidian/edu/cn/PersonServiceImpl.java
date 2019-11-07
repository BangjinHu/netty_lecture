package com.thrift.xidian.edu.cn;

import com.thrift.xidian.edu.cn.thrift.generated.DataException;
import com.thrift.xidian.edu.cn.thrift.generated.Person;
import com.thrift.xidian.edu.cn.thrift.generated.PersonService;
import org.apache.thrift.TException;

public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("got client param:" + username);
        Person person = new Person();
        person.setUsername(username);
        person.setAge(20);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
        System.out.println(person.getUsername());
    }
}
