package com.enterprise.paymybuddy.controller;

import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FriendController.class)
class FriendControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserServiceImpl userService;


  @Test
  void givenAUserWhenShowFriendFormThenSearchFriendFormDisplayed() throws Exception {
    //Given
    User user=new User("Tony","Stark","tony@test.com",
        "pw","bank",100.);
    user.setUserId(1L);

    when(userService.getUser(any())).thenReturn(java.util.Optional.of(user));

    //When
    mockMvc.perform(get("/1/add_friend"))
        .andExpect(status().isOk())
        .andExpect(view().name("friendConnexion"))

        .andExpect(content().string(containsString("Find your buddy")))
        .andExpect(content().string(containsString("Email :")));
  }

  @Test
  void givenAUserAndAFriendWhenCreateFriendConnexionThenFriendAddedDisplayed() throws Exception {
    //Given
    User user=new User("Tony","Stark","tony@test.com",
        "pw","bank",100.);
    user.setUserId(1L);

    when(userService.getUser(any())).thenReturn(java.util.Optional.of(user));
    when(userService.createFriend(any(),any())).thenReturn(true);

    //When
    mockMvc.perform(post(("/1/add_friend"))
            .param("email","steve@test.com"))

        .andExpect(status().isOk())
        .andExpect(view().name("friendConnexion"))

        .andExpect(content().string(containsString("user found and added")));
  }

  @Test
  void givenAUserWithoutFriendWhenCreateFriendConnexionThenFriendNotFoundDisplayed() throws Exception {
    //Given
    User user=new User("Tony","Stark","tony@test.com",
        "pw","bank",100.);
    user.setUserId(1L);

    when(userService.getUser(any())).thenReturn(java.util.Optional.of(user));
    when(userService.createFriend(any(),any())).thenReturn(false);

    //When
    mockMvc.perform(post(("/1/add_friend"))
            .param("email","steve@test.com"))

        .andExpect(status().isOk())
        .andExpect(view().name("friendConnexion"))

        .andExpect(content().string(containsString("user not found")));
  }

  @Test
  void givenAUserWithAAlreadyAddedFriendWhenCreateFriendConnexionThenFriendNotAddedDisplayed() throws Exception {
    //Given
    User user=new User("Tony","Stark","tony@test.com",
        "pw","bank",100.);
    user.setUserId(1L);
    User friend=new User("Steve","Roger","steve@test.com","pw","bank",100.);
    friend.setUserId(2L);

    user.getFriends().add(friend);

    when(userService.getUser(any())).thenReturn(java.util.Optional.of(user));
    when(userService.createFriend(any(),any())).thenReturn(false);

    //When
    mockMvc.perform(post(("/1/add_friend"))
            .param("email","steve@test.com"))

        .andExpect(status().isOk())
        .andExpect(view().name("friendConnexion"))

        .andExpect(content().string(containsString("friend already added")));
  }
}