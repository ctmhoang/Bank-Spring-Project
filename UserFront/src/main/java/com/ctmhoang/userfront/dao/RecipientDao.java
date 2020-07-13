package com.ctmhoang.userfront.dao;

import com.ctmhoang.userfront.domain.Recipient;
import com.ctmhoang.userfront.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipientDao extends CrudRepository<Recipient, Long> {
  List<Recipient> findRecipientsByUsr(User user);

  Recipient findRecipientByName(String name);

  void deleteByName(String name);
}
