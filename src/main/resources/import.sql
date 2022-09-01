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



INSERT INTO events (id, in_play,  event_name, participants, start_time) VALUES ( 'f15ef034-4f9a-4be1-a0c0-dd09e154e48d', true,  'Europe Championship', '[{"id": "9d9239ac-1257-11ed-861d-0242ac120002", "name": "Arsenal", "sport": "football", "country": "Moldova", "externalId": 123}, {"id": "daa0de6a-1258-11ed-861d-0242ac120002", "name": "Craiova", "sport": "football", "country": "Romania", "externalId": 231}]', '2022-04-21 ');
INSERT INTO events (id, in_play, event_name, participants, start_time) VALUES ( 'feb995f8-0c85-49c4-a7a0-f7801dafc788', false,  'Europe Championship', '[ {"id": "d9e058f0-31f1-44df-943b-ca7df75f57ae", "name": "Sheriff", "sport": "dance", "country": "Moldova", "externalId": 123},  {"id": "4e1b194c-420d-4d57-bb15-5ccd1b3b1b94", "name": "Craiova", "sport": "football", "country": "Romania",  "externalId": 231}]', '2022-11-07 ');
-- INSERT INTO events (id, in_play, event_name, participants, start_time) VALUES ( '092aa783-420b-4bcb-ad9b-18ac902b9132', false,   'Dance Championship', '[ {"id": "d9e058f0-31f1-44df-943b-ca7df75f57ae", "name": "Sheriff", "sport": "dance", "country": "Moldova", "externalId": 123},  {"id": "5af40425-8b62-4fa3-94be-4babccfe97ea", "name": "Spartak", "sport": "dance", "country": "Russia", "externalId": 980 }]', '2021-09-10 ');
-- INSERT INTO events (id, in_play,  event_name, participants, start_time) VALUES ( '7293db19-bd20-47b7-b922-2508f083c355', true,   'Basketball Championship', '[ { "id": "16b8c81a-f6ff-4642-9db5-c3fb799f0c7a","name": "Dallas Mavericks", "sport": "basketball", "country": "USA","externalId": 6421},   {"id": "44404a13-48b2-464b-91ee-3465968337be", "name": "Boston Celtics", "sport": "basketball","country": "USA","externalId": 53289}]', '2022-05-04 ');




INSERT INTO markets (id, market_name, market_template_name, event_id, selections) values ( '2f183e94-2522-11ed-861d-0242ac120002',  'Match Odds', 'MO', 'f15ef034-4f9a-4be1-a0c0-dd09e154e48d', '[{"name":"test_549b0fc9785b", "price":62.17},{"name":"test_549b0fc9785b", "price":62.17}]');
INSERT INTO markets (id, market_name, market_template_name, event_id, selections) values ( '2f1841fa-2522-11ed-861d-0242ac120002', 'Double Chance', 'DC', 'f15ef034-4f9a-4be1-a0c0-dd09e154e48d', '[{"name":"test_549b0fc9785b", "price":62.17},{"name":"test_549b0fc9785b", "price":62.17}]');
INSERT INTO markets (id, market_name, market_template_name, event_id, selections) values ( '2f18454c-2522-11ed-861d-0242ac120002', 'Draw No Bet', 'DNB', 'f15ef034-4f9a-4be1-a0c0-dd09e154e48d', '[{"name":"test_549b0fc9785b", "price":62.17},{"name":"test_549b0fc9785b", "price":62.17}]');
INSERT INTO markets (id, market_name, market_template_name, event_id, selections) values ( '2f184704-2522-11ed-861d-0242ac120002',  'Correct Score', 'CS', 'f15ef034-4f9a-4be1-a0c0-dd09e154e48d', '[{"name":"test_549b0fc9785b", "price":62.17},{"name":"test_549b0fc9785b", "price":62.17}]');
INSERT INTO markets (id, market_name, market_template_name, event_id, selections) values ( '2f184a4c-2522-11ed-861d-0242ac120002',  'Over/Under Bets', 'O/UB', 'f15ef034-4f9a-4be1-a0c0-dd09e154e48d', '[{"name":"test_549b0fc9785b", "price":62.17},{"name":"test_549b0fc9785b", "price":62.17}]');

INSERT INTO markets (id, market_name, market_template_name, event_id, selections) values ( 'b1944f2a-2f58-4edb-8813-24429d5ab029',  'Match Odds', 'MO', 'feb995f8-0c85-49c4-a7a0-f7801dafc788', '[{"name":"test_549b0fc9785b", "price":62.17},{"name":"test_549b0fc9785b", "price":62.17}]');
INSERT INTO markets (id, market_name, market_template_name, event_id, selections) values ( 'c7398c0e-1995-4074-878f-f2cf4dc33ab4', 'Double Chance', 'DC', 'feb995f8-0c85-49c4-a7a0-f7801dafc788', '[{"name":"test_549b0fc9785b", "price":62.17},{"name":"test_549b0fc9785b", "price":62.17}]');
INSERT INTO markets (id, market_name, market_template_name, event_id, selections) values ( '135a5817-ca14-4c0e-a61e-bf10dac107fa', 'Draw No Bet', 'DNB', 'feb995f8-0c85-49c4-a7a0-f7801dafc788', '[{"name":"test_549b0fc9785b", "price":62.17},{"name":"test_549b0fc9785b", "price":62.17}]');
INSERT INTO markets (id, market_name, market_template_name, event_id, selections) values ( '739aed25-0ccb-44a5-877f-c2a3831f56a9',  'Correct Score', 'CS', 'feb995f8-0c85-49c4-a7a0-f7801dafc788', '[{"name":"test_549b0fc9785b", "price":62.17},{"name":"test_549b0fc9785b", "price":62.17}]');
INSERT INTO markets (id, market_name, market_template_name, event_id, selections) values ( 'f7f1ba46-94eb-412f-96f7-adeaf9430f51',  'Over/Under Bets', 'O/UB', 'feb995f8-0c85-49c4-a7a0-f7801dafc788', '[{"name":"test_549b0fc9785b", "price":62.17},{"name":"test_549b0fc9785b", "price":62.17}]')