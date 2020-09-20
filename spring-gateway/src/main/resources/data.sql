INSERT INTO user (username, password, enabled)
VALUES ('user1', '$2a$10$H3AC0dO1mZKVzHTpJ4pKouC94hsy11K3xK/GZl5ih51F6R/mWMn9e', TRUE);
INSERT INTO user_role (id, username, role)
VALUES (1, 'user1', 'USER_ROLE');

INSERT INTO user (username, password, enabled)
VALUES ('user2', '$2a$10$UDRhp6tvx15/Tkg81H2uy.gvtImhdsWj7xtdLP2mZ98lbFm8ySoGG', TRUE);
INSERT INTO user_role (id, username, role)
VALUES (2, 'user2', 'ADMIN_ROLE');