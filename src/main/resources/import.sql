INSERT INTO participants (id, country, external_id, name, sport) VALUES ('9d9239ac-1257-11ed-861d-0242ac120002', 'Moldova', '001', 'Bravo Jazz Team', 'dance');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('16b8c81a-f6ff-4642-9db5-c3fb799f0c7a', 'USA', '002', 'Dallas Mavericks', 'basketball');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('44404a13-48b2-464b-91ee-3465968337be', 'USA', '003', 'Boston Celtics', 'basketball');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('daa0de6a-1258-11ed-861d-0242ac120002', 'Romania', '004', 'Encore Jazz Team', 'dance');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('5af40425-8b62-4fa3-94be-4babccfe97ea', 'Russia', '005', 'Diversity', 'dance');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('4e1b194c-420d-4d57-bb15-5ccd1b3b1b94', 'Romania', '010', 'Craiova', 'football');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('d9e058f0-31f1-44df-943b-ca7df75f57ae', 'Moldova', '012', 'Sheriff', 'football');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('785a6732-a1b4-4254-9499-8c9a732af55b', 'Moldova', '015', 'Jabbawockeez', 'dance');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('72557cdf-6eef-42d5-8762-5efd93f609b7', 'USA', '019', 'Katie Robinson', 'horsemanship');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('f1488109-a44c-4958-a740-0d052e3d9197', 'USA', '014', 'William H. Weeks', 'horsemanship');



INSERT INTO events (id, in_play, event_name, participants, start_time) VALUES ('f15ef034-4f9a-4be1-a0c0-dd09e154e48d', true, 'International Dance Games', '[{"id": "9d9239ac-1257-11ed-861d-0242ac120002", "name": "Bravo Jazz Team", "sport": "dance", "country": "Moldova", "externalId": "001"}, {"id": "daa0de6a-1258-11ed-861d-0242ac120002", "name": "Encore Jazz Team", "sport": "dance", "country": "Romania", "externalId": "004"}]', '2022-11-25');
INSERT INTO events (id, in_play, event_name, participants, start_time) VALUES ('feb995f8-0c85-49c4-a7a0-f7801dafc788', true, 'Europe Championship',  '[ {"id": "d9e058f0-31f1-44df-943b-ca7df75f57ae", "name": "Sheriff", "sport": "football", "country": "Moldova", "externalId": "012"},  {"id": "4e1b194c-420d-4d57-bb15-5ccd1b3b1b94", "name": "Craiova", "sport": "football", "country": "Romania",  "externalId": "010"}]',  '2022-11-25');
INSERT INTO events (id, in_play, event_name, participants, start_time) VALUES ('092aa783-420b-4bcb-ad9b-18ac902b9132', false, 'Dance Championship', '[ {"id": "785a6732-a1b4-4254-9499-8c9a732af55b", "name": "Jabbawockeez", "sport": "dance", "country": "Moldova", "externalId": "015"},  {"id": "5af40425-8b62-4fa3-94be-4babccfe97ea", "name": "Diversity", "sport": "dance", "country": "Russia", "externalId": "005" }]', '2022-11-28');
INSERT INTO events (id, in_play, event_name, participants, start_time) VALUES ('7293db19-bd20-47b7-b922-2508f083c355', true, 'Basketball Championship', '[ { "id": "16b8c81a-f6ff-4642-9db5-c3fb799f0c7a","name": "Dallas Mavericks", "sport": "basketball", "country": "USA","externalId": "002"},   {"id": "44404a13-48b2-464b-91ee-3465968337be", "name": "Boston Celtics", "sport": "basketball","country": "USA","externalId": "003"}]', '2023-05-04');
INSERT INTO events (id, in_play, event_name, participants, start_time) VALUES ('eda63a1e-8fba-4d1c-ad55-4d899e9dccc9', false, 'National Horse Show Lexington', '[ { "id": "72557cdf-6eef-42d5-8762-5efd93f609b7","name": "Katie Robinson", "sport": "horsemanship", "country": "USA","externalId": "019"},   {"id": "f1488109-a44c-4958-a740-0d052e3d9197", "name": "William H. Weeks", "sport": "horsemanship","country": "USA","externalId": "014"}]', '2022-12-22');

INSERT INTO markets (id, market_name, market_template_name, event_id) values ('940ec9e2-cc4d-4c3f-a9cc-a804760272f8', 'Double Chance', 'DC', 'f15ef034-4f9a-4be1-a0c0-dd09e154e48d');
INSERT INTO markets (id, market_name, market_template_name, event_id) values ('90f0895e-06f7-4d8c-a01f-534236d29980', 'Match Odds', 'MO', 'f15ef034-4f9a-4be1-a0c0-dd09e154e48d');

INSERT INTO markets (id, market_name, market_template_name, event_id) values ('2f1841fa-2522-11ed-861d-0242ac120002', 'Double Chance', 'DC', 'feb995f8-0c85-49c4-a7a0-f7801dafc788');
INSERT INTO markets (id, market_name, market_template_name, event_id) values ('11050d92-c1b2-45ab-ba5c-91128301cab1', 'Correct Score', 'DC', 'feb995f8-0c85-49c4-a7a0-f7801dafc788');
INSERT INTO markets (id, market_name, market_template_name, event_id) values ('6ba669dd-3685-40d5-935f-a7bdaea6a546', 'Draw No Bet', 'DNB', '092aa783-420b-4bcb-ad9b-18ac902b9132');
INSERT INTO markets (id, market_name, market_template_name, event_id) values ('74c79923-3fac-45cd-9e05-7725b58618a6', 'Double Chance', 'dc', '092aa783-420b-4bcb-ad9b-18ac902b9132');
INSERT INTO markets (id, market_name, market_template_name, event_id) values ('7e7f54c5-d7f8-421a-827e-bdf9bf3c929d', 'Correct Score', 'CS', '7293db19-bd20-47b7-b922-2508f083c355');
INSERT INTO markets (id, market_name, market_template_name, event_id) values ('b21a22dd-4fc6-4593-99e2-269f981de3b3', 'Double Chance', 'CS', '7293db19-bd20-47b7-b922-2508f083c355');
INSERT INTO markets (id, market_name, market_template_name, event_id) values ('74de0758-3fb0-4bc6-bc79-2fd097f847ab', 'To win the race', 'WR', 'eda63a1e-8fba-4d1c-ad55-4d899e9dccc9');
INSERT INTO markets (id, market_name, market_template_name, event_id) values ('8c039042-4e3b-4038-a217-7c36681ec66e', 'Each Way', 'EW', 'eda63a1e-8fba-4d1c-ad55-4d899e9dccc9');
INSERT INTO markets (id, market_name, market_template_name, event_id) values ('6cf43d44-13ea-48af-9eec-ca44ddf3e757', 'Forecast', 'FC', 'eda63a1e-8fba-4d1c-ad55-4d899e9dccc9');


INSERT INTO selections(id, selection_name, price, market_id) values ('24c40c66-e410-45ab-b9b3-cec708d405d6', 'aaaaaa', 5.59, '940ec9e2-cc4d-4c3f-a9cc-a804760272f8');
INSERT INTO selections(id, selection_name, price, market_id) values ('d8d55f47-dc96-4944-b6dd-88692deb9c3f', 'aaaaaa', 1.43, '940ec9e2-cc4d-4c3f-a9cc-a804760272f8');

INSERT INTO selections(id, selection_name, price, market_id) values ('0b13d2e4-edba-41a4-889b-2b11d24544a2', 'bbb', 23.45, '90f0895e-06f7-4d8c-a01f-534236d29980');
INSERT INTO selections(id, selection_name, price, market_id) values ('148d757e-769f-4a8c-9b10-f08484d0c7a8', 'bb', 2.79, '90f0895e-06f7-4d8c-a01f-534236d29980');
INSERT INTO selections(id, selection_name, price, market_id) values ('873c13f6-0533-4667-b70a-cb20a67122de', 'bb', 56.98, '90f0895e-06f7-4d8c-a01f-534236d29980');
INSERT INTO selections(id, selection_name, price, market_id) values ('eb235fbe-1503-4b26-bb80-c72c3872d9a6', 'ccc', 2.79, '2f1841fa-2522-11ed-861d-0242ac120002');
INSERT INTO selections(id, selection_name, price, market_id) values ('f04e745d-c604-4e8f-88f7-6ca21249f2e3', 'cc', 5.45, '2f1841fa-2522-11ed-861d-0242ac120002');
INSERT INTO selections(id, selection_name, price, market_id) values ('1fcee029-ef08-4514-8e20-f78d8e6bd79d', 'cc', 5.79, '2f1841fa-2522-11ed-861d-0242ac120002');
INSERT INTO selections(id, selection_name, price, market_id) values ('453b4ca9-446b-45b9-88af-96d2e886dd14', 'cc', 8.64, '2f1841fa-2522-11ed-861d-0242ac120002');
INSERT INTO selections(id, selection_name, price, market_id) values ('b7cbc6f3-52f1-470e-8dbb-50c56de764fb', 'dd', 9.54, '6ba669dd-3685-40d5-935f-a7bdaea6a546');
INSERT INTO selections(id, selection_name, price, market_id) values ('c3d82fcf-8db6-4e6c-a18d-6c431faaa948', 'ee', 6.32, '7e7f54c5-d7f8-421a-827e-bdf9bf3c929d');
INSERT INTO selections(id, selection_name, price, market_id) values ('fd22f5b9-39b8-4dd3-b1cd-db096f737733', 'ee', 3.32, '7e7f54c5-d7f8-421a-827e-bdf9bf3c929d');
INSERT INTO selections(id, selection_name, price, market_id) values ('20e11e75-a03a-4f78-aa75-569cdddb25c7', 'ff', 5.98, '940ec9e2-cc4d-4c3f-a9cc-a804760272f8');
INSERT INTO selections(id, selection_name, price, market_id) values ('a4277ac7-0eca-4126-b31d-056f92e840d9', 'fff', 8.44, '940ec9e2-cc4d-4c3f-a9cc-a804760272f8');
INSERT INTO selections(id, selection_name, price, market_id) values ('d9c84bf0-9092-4540-9bcc-0e083d7e5644', 'fff', 3.87, '940ec9e2-cc4d-4c3f-a9cc-a804760272f8');
INSERT INTO selections(id, selection_name, price, market_id) values ('9f98e528-e27d-40d1-b7e1-bb13feeb40f8', 'gggg', 9.75, 'b21a22dd-4fc6-4593-99e2-269f981de3b3');
INSERT INTO selections(id, selection_name, price, market_id) values ('7c5c7b3c-1c98-4a03-920e-baec6e1b92b4', 'ggg', 1.54, 'b21a22dd-4fc6-4593-99e2-269f981de3b3');
INSERT INTO selections(id, selection_name, price, market_id) values ('74ba55dd-b482-4cde-a014-7e160c2039d9', 'gg', 9.31, 'b21a22dd-4fc6-4593-99e2-269f981de3b3');
INSERT INTO selections(id, selection_name, price, market_id) values ('3cd7b0da-ee47-4ab8-99c8-b7cc5e5f7e0a', 'hh', 1.62, '74de0758-3fb0-4bc6-bc79-2fd097f847ab');
INSERT INTO selections(id, selection_name, price, market_id) values ('c1b4d2ee-9a4f-4b20-a370-572002960d53', 'hhh', 7.31, '74de0758-3fb0-4bc6-bc79-2fd097f847ab');
INSERT INTO selections(id, selection_name, price, market_id) values ('1ada0fab-edcf-4e9b-9d3b-e8acdcf8b7cd', 'iii', 4.62, '8c039042-4e3b-4038-a217-7c36681ec66e');
INSERT INTO selections(id, selection_name, price, market_id) values ('3bbc35f9-43e2-4d46-9b02-2a59c531ed98', 'ii', 2.01, '8c039042-4e3b-4038-a217-7c36681ec66e');
INSERT INTO selections(id, selection_name, price, market_id) values ('6b31d520-1f11-45aa-8850-896925fb26ec', 'iiii', 4.92, '8c039042-4e3b-4038-a217-7c36681ec66e');
INSERT INTO selections(id, selection_name, price, market_id) values ('f717c5fe-fb99-4f36-a129-d90b34425a72', 'iii', 5.91, '8c039042-4e3b-4038-a217-7c36681ec66e');
INSERT INTO selections(id, selection_name, price, market_id) values ('a6371463-00e0-4af4-9cee-b66065c8da60', 'jj', 6.53, '6cf43d44-13ea-48af-9eec-ca44ddf3e757');
INSERT INTO selections(id, selection_name, price, market_id) values ('cc6af87c-21ab-4008-bef2-3276a29994ea', 'jjjj', 2.03, '6cf43d44-13ea-48af-9eec-ca44ddf3e757');

