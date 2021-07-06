var Zone;
(function (Zone) {
    Zone[Zone["alsace-moselle"] = 0] = "alsace-moselle";
    Zone[Zone["guadeloupe"] = 1] = "guadeloupe";
    Zone[Zone["guyane"] = 2] = "guyane";
    Zone[Zone["la-reunion"] = 3] = "la-reunion";
    Zone[Zone["martinique"] = 4] = "martinique";
    Zone[Zone["mayotte"] = 5] = "mayotte";
    Zone[Zone["metropole"] = 6] = "metropole";
    Zone[Zone["nouvelle-caledonie"] = 7] = "nouvelle-caledonie";
    Zone[Zone["polynesie-francaise"] = 8] = "polynesie-francaise";
    Zone[Zone["saint-barthelemy"] = 9] = "saint-barthelemy";
    Zone[Zone["saint-martin"] = 10] = "saint-martin";
    Zone[Zone["saint-pierre-et-miquelon"] = 11] = "saint-pierre-et-miquelon";
    Zone[Zone["wallis-et-futuna"] = 12] = "wallis-et-futuna";
})(Zone || (Zone = {}));
var ojd = new Date();
var currentYear = ojd.getFullYear();
var zone = Zone[Zone.metropole];
var url = "https://calendrier.api.gouv.fr/jours-feries/" + zone + "/" + currentYear + ".json";
fetch(url)
    .then(function (response) { return response.json(); })
    .then(function (data) {
    console.log(data);
    for (var _i = 0, data_1 = data; _i < data_1.length; _i++) {
        var date = data_1[_i];
        console.log("test2" + date);
    }
})
    .catch(function (e) { return console.log("erreur : " + e); });
