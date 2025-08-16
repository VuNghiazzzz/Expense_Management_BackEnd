package com.example.Expense.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String username;
        private String email;
        private String password;

        @OneToMany(mappedBy = "user")
        private List<Category> categories;

        @OneToMany(mappedBy = "user")
        private List<Transaction> transactions;

        public User() {}

        public User(Long id, String username, String email, String password) {
                this.id = id;
                this.username = username;
                this.email = email;
                this.password = password;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public List<Category> getCategories() {
                return categories;
        }

        public void setCategories(List<Category> categories) {
                this.categories = categories;
        }

        public List<Transaction> getTransactions() {
                return transactions;
        }

        public void setTransactions(List<Transaction> transactions) {
                this.transactions = transactions;
        }


}

