<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>TP1</h1>
    <a href="Module 02 - Enoncé TP 02 - Tableaux.pdf" target="_blank">Enoncé</a>
    <a href="Module 02 - Solution TP 02 - Tableaux.pdf" target="_blank">Solution</a>
    <br>
    <?php
        # exo 1 
        $tab = [
            'marley' => [
                'prenom' => 'bob',
                'ville' => 'en jamaique',
                'residence' => 'cimetiere',
                'age' => 'vieux'
            ],
            'bidule' => [
                    'prenom' => 'henri',
                    'ville' => 'Paris',
                    'residence' => 'dans un hotel',
                    'age' => '42'
            ]
        ];
        //echo $tab['bidule']['ville'];

        # exo 2 
        $tab2 = [
            'marley' => [
                'bob' => [
                    'ville' => 'en jamaique',
                    'residence' => 'cimetiere',
                    'age' => 'vieux'
                ]

            ],
            'bidule' => [
                    'henri' => [
                        'ville' => 'Paris',
                        'residence' => 'dans un hotel',
                        'age' => '42'
                    ]
            ]
        ];
        //echo $tab['bidule']['henri']['ville'];
        echo '<br>';
        
        # exo 3 
        foreach($tab as $value){
            echo "element 0 : " . $value['prenom'] . "<br>"; 
            echo "element 1 : " . $value['ville'] . "<br>"; 
            echo "element 2 : " . $value['age'] . "<br>"; 
        }

        echo '<br>';

        foreach($tab2 as $key => $value){
            foreach($value as $value2){
                echo "element 0 : " . $key . "<br>";
                echo "element 1 : " . $value2['ville'] . "<br>"; 
                echo "element 2 : " . $value2['age'] . "<br>"; 
            }

        }
    ?>
</body>
</html>