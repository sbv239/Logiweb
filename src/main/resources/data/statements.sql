SELECT setval('trucks_id_seq', (SELECT max(id) FROM trucks));
SELECT setval('drivers_id_seq', (SELECT max(id) FROM drivers));