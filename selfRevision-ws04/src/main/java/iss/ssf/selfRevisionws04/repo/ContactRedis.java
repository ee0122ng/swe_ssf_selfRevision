package iss.ssf.selfRevisionws04.repo;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import iss.ssf.selfRevisionws04.model.Contact;

@Repository
public class ContactRedis {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private List<Object> addressList = new LinkedList<>();
    private List<Contact> ctcList = new LinkedList<>();

    public void saveContact(Contact ctc) {
        redisTemplate.opsForList().leftPush("addressList", ctc.getId());
        redisTemplate.opsForHash().put("addressbookMap", ctc.getId(), ctc);
    }

    public Contact getContactById(String id) {

        return (Contact) redisTemplate.opsForHash().get("addressbookMap", id);
    }

    public List<Contact> findAll(Integer startIndex) {

        // return the first 10 contacts
        addressList = redisTemplate.opsForList().range("addressList", startIndex, 10);
        ctcList = redisTemplate.opsForHash()
                                .multiGet("addressbookMap", addressList)
                                .stream()
                                .filter(Contact.class::isInstance)
                                .map(Contact.class::cast)
                                .collect(Collectors.toList());

        return ctcList;
    }

    // public void deleteAll() {

    //     if (redisTemplate.opsForList().size("addressList") != 0) {

    //         for (Object obj : this.addressList) {
    //             redisTemplate.opsForHash().delete("addressbookMap", obj);
    //         }
    
    //         Long i = 1L;
    //         while (i != null) {
    //             redisTemplate.opsForList().leftPop("addressList");
    //             i = redisTemplate.opsForList().size("addressList");
    //         }

    //     }

    // }
    
}
