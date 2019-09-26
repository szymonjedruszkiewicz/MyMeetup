INSERT INTO role values (1, 'ROLE_ADMIN');
INSERT INTO role values (2, 'ROLE_USER');
INSERT INTO user values (1, 'admin@sda.pl', '$2a$10$rOFS.O6.E63278YBdWZilumR.vxePh1Z5G7aJtAG5VeYZYz0nxgNW','ADMIN');
INSERT INTO user values (2, 'test@test.pl', '$2a$10$X48BnytTC36AHONPOQruculRlq9yER1xOGIi1zii9KZCbpRx5FYoW','testuser');
INSERT INTO user_roles values (1, 1);
INSERT INTO user_roles values (2, 2);
INSERT INTO event values (1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin nibh augue, suscipit a, scelerisque sed, lacinia in, mi. Cras vel lorem.', '2019-10-02', '2019-10-01', 'Test Event 1', 1);
INSERT INTO event values (2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin nibh augue, suscipit a, scelerisque sed, lacinia in, mi. Cras vel lorem.', '2019-09-30', '2019-09-01', 'Test Event 2', 1);
INSERT INTO event values (3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin nibh augue, suscipit a, scelerisque sed, lacinia in, mi. Cras vel lorem.', '2019-10-02', '2019-09-01', 'Test Event 3', 1);
INSERT INTO event values (4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin nibh augue, suscipit a, scelerisque sed, lacinia in, mi. Cras vel lorem.', '2019-12-02', '2019-10-01', 'Test Event 4', 1);
INSERT INTO comment values (1, '2019-09-01 12:01:10', 'just some test comment', 2, 1);
INSERT INTO comment values (2, '2019-09-02 12:01:10', 'another comment', 1, 1);
INSERT INTO comment values (3, '2019-09-02 10:01:10', 'another comment', 2, 1);
INSERT INTO comment values (4, '2019-09-02 10:01:10', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin nibh augue, suscipit a, scelerisque sed, lacinia in, mi. Cras vel lorem.', 3, 1);
