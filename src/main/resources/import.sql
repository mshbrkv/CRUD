INSERT INTO participants (id, country, external_id, name, sport) VALUES ('9d9239ac-1257-11ed-861d-0242ac120002', 'Moldova', 123, 'Arsenal', 'football');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('16b8c81a-f6ff-4642-9db5-c3fb799f0c7a', 'USA', 6421, 'Dallas Mavericks', 'basketball');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('44404a13-48b2-464b-91ee-3465968337be', 'USA', 53289, 'Boston Celtics', 'basketball');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('daa0de6a-1258-11ed-861d-0242ac120002', 'Romania', 231, 'Craiova', 'football');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('5af40425-8b62-4fa3-94be-4babccfe97ea', 'Russia', 980, 'Spartak', 'dance');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('e6316141-4540-401d-9385-02afd6c133da', 'Moldova', 123, 'Spartak', 'dance');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('ccad34ac-e485-4648-83cf-82a29f4e7ca9', 'Romania', 231, 'Spartak', 'football');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('a548fc36-493e-4fec-ac85-c4dbdbd71d4c', 'Russia', 980, 'Spartak', 'football');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('3e86269c-7281-41b9-b2e9-737ac88bd569', 'Moldova', 123, 'Colibri', 'dance');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('4e1b194c-420d-4d57-bb15-5ccd1b3b1b94', 'Romania', 231, 'Craiova', 'football');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('e7f454ad-c09b-445c-966d-35ed42651c8a', 'UK', 980, 'Arsenal', 'football');
INSERT INTO participants (id, country, external_id, name, sport) VALUES ('d9e058f0-31f1-44df-943b-ca7df75f57ae', 'Moldova', 123, 'Sheriff', 'dance');



INSERT INTO events (id, in_play,  event_name, participants, start_time) VALUES ( 'f15ef034-4f9a-4be1-a0c0-dd09e154e48d', true,  'International Dance Games', '[{"id": "9d9239ac-1257-11ed-861d-0242ac120002", "name": "Arsenal", "sport": "football", "country": "Moldova", "externalId": 123}, {"id": "daa0de6a-1258-11ed-861d-0242ac120002", "name": "Craiova", "sport": "football", "country": "Romania", "externalId": 231}]', '2022-04-21 ');
INSERT INTO events (id, in_play, event_name, participants, start_time) VALUES ( 'feb995f8-0c85-49c4-a7a0-f7801dafc788', false,  'Europe Championship', '[ {"id": "d9e058f0-31f1-44df-943b-ca7df75f57ae", "name": "Sheriff", "sport": "dance", "country": "Moldova", "externalId": 123},  {"id": "4e1b194c-420d-4d57-bb15-5ccd1b3b1b94", "name": "Craiova", "sport": "football", "country": "Romania",  "externalId": 231}]', '2022-11-07 ');
INSERT INTO events (id, in_play, event_name, participants, start_time) VALUES ( '092aa783-420b-4bcb-ad9b-18ac902b9132', false,   'Dance Championship', '[ {"id": "d9e058f0-31f1-44df-943b-ca7df75f57ae", "name": "Sheriff", "sport": "dance", "country": "Moldova", "externalId": 123},  {"id": "5af40425-8b62-4fa3-94be-4babccfe97ea", "name": "Spartak", "sport": "dance", "country": "Russia", "externalId": 980 }]', '2021-09-10 ');
INSERT INTO events (id, in_play,  event_name, participants, start_time) VALUES ( '7293db19-bd20-47b7-b922-2508f083c355', true,   'Basketball Championship', '[ { "id": "16b8c81a-f6ff-4642-9db5-c3fb799f0c7a","name": "Dallas Mavericks", "sport": "basketball", "country": "USA","externalId": 6421},   {"id": "44404a13-48b2-464b-91ee-3465968337be", "name": "Boston Celtics", "sport": "basketball","country": "USA","externalId": 53289}]', '2022-05-04 ');




INSERT INTO markets (id, market_name, market_template_name, event_id) values ( '90f0895e-06f7-4d8c-a01f-534236d29980',  'Match Odds', 'MO', 'f15ef034-4f9a-4be1-a0c0-dd09e154e48d');
INSERT INTO markets (id, market_name, market_template_name, event_id) values ( '2f1841fa-2522-11ed-861d-0242ac120002', 'Double Chance', 'DC', 'feb995f8-0c85-49c4-a7a0-f7801dafc788');
INSERT INTO markets (id, market_name, market_template_name, event_id) values ( '6ba669dd-3685-40d5-935f-a7bdaea6a546', 'Draw No Bet', 'DNB', '092aa783-420b-4bcb-ad9b-18ac902b9132');
INSERT INTO markets (id, market_name, market_template_name, event_id) values ( '7e7f54c5-d7f8-421a-827e-bdf9bf3c929d',  'Correct Score', 'CS', '7293db19-bd20-47b7-b922-2508f083c355');
-- INSERT INTO markets (id, market_name, market_template_name, event_id) values ( 'b21a22dd-4fc6-4593-99e2-269f981de3b3',  'Correct Score', 'CS', '7293db19-bd20-47b7-b922-2508f083c355');


INSERT INTO selections(id, selection_name, price, market_id) values ('940ec9e2-cc4d-4c3f-a9cc-a804760272f8','aaaaaa',0.79,'90f0895e-06f7-4d8c-a01f-534236d29980');
INSERT INTO selections(id, selection_name, price, market_id ) values ('eb235fbe-1503-4b26-bb80-c72c3872d9a6','bbbbbbb', 2.79,'2f1841fa-2522-11ed-861d-0242ac120002');
INSERT INTO selections(id, selection_name, price, market_id) values ('453b4ca9-446b-45b9-88af-96d2e886dd14','ssssssss',0.6463,'2f1841fa-2522-11ed-861d-0242ac120002');
INSERT INTO selections(id, selection_name, price, market_id ) values ('b7cbc6f3-52f1-470e-8dbb-50c56de764fb','dddddddd', 0.54,'6ba669dd-3685-40d5-935f-a7bdaea6a546');
INSERT INTO selections(id, selection_name, price, market_id ) values ('c3d82fcf-8db6-4e6c-a18d-6c431faaa948','eeeee', 56.332,'7e7f54c5-d7f8-421a-827e-bdf9bf3c929d');
