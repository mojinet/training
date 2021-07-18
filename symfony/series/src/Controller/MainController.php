<?php


namespace App\Controller;
use App\Entity\Serie;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;

class MainController extends AbstractController
{
    /**
     * @Route("/", name="main_home")
     */
    public function home(){
        $prenom = "Jean-Baptiste";
        $age = 33;
        $ville = "La ferte sous jouarre";
        return $this->render('main/home.html.twig',compact("prenom","age","ville"));
    }

    /**
     * @Route("/test", name="main_test")
     */
    public function test(){
        return $this->render('main/test.html.twig');
    }

    /**
     * @Route("/demo", name="main_demo")
     */
    public function demo(EntityManagerInterface $entityManager){
        // créer une instance de mon entité
        $serie = new Serie();

        // hydrate toute les propriété
        $serie->setName('pif');
        $serie->setBackdrop('paf');
        $serie->setPoster('pouf');
        $serie->setDateCreated(new \DateTime());
        $serie->setFirstAirDate(new \DateTime("- 1 year"));
        $serie->setLastAirDate(new \DateTime("- 6 month"));
        $serie->setGenres('drama');
        $serie->setOverview('une histoire pas comme les autres');
        $serie->setPopularity(125.00);
        $serie->setVote(7.2);
        $serie->setStatus('Canceled');
        $serie->setTmdbId(384314);

        // pour visualiser notre objet dans la toolbar de symfony
        // ici notre objet n'a pas d'id
        dump($serie);

        // enregistre en base de données
        $entityManager->persist($serie);
        $entityManager->flush();

        // pour visualiser notre objet dans la toolbar de symfony
        // ici notre objet à bien un id des lors qu'il as été ajouté
        dump($serie);

        // modifier la serie
        $serie->setGenres('Fiction');
        $entityManager->persist($serie);
        $entityManager->flush();

        //suprimme de la base de données
        $entityManager->remove($serie);
        $entityManager->flush();

        return $this->render('main/create.html.twig');
    }
}

