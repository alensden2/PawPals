import React, { useEffect, useContext } from 'react';
import useStyles from './PetHealthAndDiseaseInfo.styles';
import { HeaderContext } from '@src/context';

const ARTICLES = [
  {
    imgSrc:
      'https://images.pexels.com/photos/982300/pexels-photo-982300.jpeg?auto=compress&cs=tinysrgb&w=600',
    imgAlt: 'Dog',
    title: 'Study Shows Dogs Can Help Reduce Stress',
    content:
      'A recent study conducted by the American Veterinary Medical Association shows that spending time with dogs can significantly reduce stress levels in humans.',
    link: 'https://www.heart.org/en/healthy-living/healthy-bond-for-life-pets/pets-as-coworkers/pets-and-mental-health#:~:text=Just%20playing%20with%20a%20dog,the%20person%20and%20their%20pet.'
  },
  {
    imgSrc:
      'https://images.pexels.com/photos/14244317/pexels-photo-14244317.jpeg?auto=compress&cs=tinysrgb&w=600',
    imgAlt: 'Cat',
    title: 'New Feline Vaccine Protects Against Common Diseases',
    content:
      'A new vaccine for cats has been released that provides protection against several common diseases, including feline distemper and feline leukemia.',
    link: 'https://www.littlemiamivetclinic.com/site/blog/2022/03/15/fvrcp-cat-vaccine#:~:text=The%20FVRCP%20vaccine%20is%20an,and%20feline%20panleukopenia%20(P).'
  },
  {
    imgSrc:
      'https://media.istockphoto.com/id/1164293619/photo/french-bulldog-cooling-off-next-to-a-mini-electric-fan.jpg?b=1&s=612x612&w=0&k=20&c=ca5VCy_ARTwoYv4NW2cjmZN49mmiRHuFNZr0kvntZvY=',
    imgAlt: 'Dog',
    title: 'How to Keep Your Dog Safe During a Heatwave',
    content:
      "With the temperatures rising, it's important to know how to keep your dog safe during a heatwave. Here are some tips to help keep your furry friend cool and comfortable.",
    link: 'https://www.akc.org/expert-advice/health/how-to-keep-dog-safe-heat/'
  },
  {
    imgSrc:
      'https://images.pexels.com/photos/14244317/pexels-photo-14244317.jpeg?auto=compress&cs=tinysrgb&w=600',
    imgAlt: 'Cat',
    title: 'Common Health Issues in Senior Cats',
    content:
      'As cats age, they become more susceptible to certain health issues. Learn about some of the most common health concerns for senior cats and how to keep them healthy and comfortable in their golden years.',
    link: 'https://www.petmd.com/cat/slideshows/8-common-health-issues-senior-cats'
  },
  {
    title: 'Beware of Ticks: Lyme Disease in Dogs',
    imgSrc:
      'https://images.pexels.com/photos/2833788/pexels-photo-2833788.jpeg?auto=compress&cs=tinysrgb&w=600',
    alt: 'Dog with tick',
    content:
      'Lyme disease is a bacterial infection transmitted by ticks that can affect dogs, causing symptoms such as fever, lethargy, and joint pain. Learn how to protect your pet from ticks and spot the signs of Lyme disease.',
    link: 'https://www.akc.org/expert-advice/health/lyme-disease-in-dogs/'
  },
  {
    title: 'Understanding Hair Loss in Cats',
    imgSrc:
      'https://images.pexels.com/photos/10813423/pexels-photo-10813423.jpeg?auto=compress&cs=tinysrgb&w=600',
    imgAlt: 'Cat with hair loss',
    content:
      "If you've noticed your cat losing hair, it could be a sign of an underlying medical condition such as allergies, parasites, or hormonal imbalances. Find out what may be causing your cat's hair loss and how to treat it.",
    link: 'https://www.petmd.com/cat/conditions/skin/c_ct_alopecia'
  },
  {
    title: 'Dealing with Doggy Diarrhea',
    imgSrc:
      'https://media.istockphoto.com/id/1193794626/photo/dog-at-park.jpg?b=1&s=612x612&w=0&k=20&c=76qbkGMoODK20mzaqIjUYzNLUnw1tU_Q9gu-56KSDXQ=',
    imgAlt: 'Dog with upset stomach',
    content:
      "Diarrhea can be a common problem for dogs, but it can also be a sign of a more serious issue. Learn about the causes of diarrhea in dogs and what you can do to prevent it, as well as when it's time to see a vet.",
    link: 'https://www.akc.org/expert-advice/health/diarrhea-in-dogs/'
  },
  {
    title: 'Common Health Problems in Dogs and How to Prevent Them',
    imgSrc:
      'https://images.pexels.com/photos/4335591/pexels-photo-4335591.jpeg?auto=compress&cs=tinysrgb&w=600',
    imgAlt: 'Dog',
    content:
      'Dogs are prone to several common health problems, including obesity, dental disease, and arthritis. Learn how to prevent these issues from affecting your pet.',
    link: 'https://www.pawlicy.com/blog/dog-health-issues/#:~:text=Nearly%2060%25%20of%20dogs%20are,and%20give%20them%20regular%20exercise.'
  },

  {
    imgSrc:
      'https://images.pexels.com/photos/6397911/pexels-photo-6397911.jpeg?auto=compress&cs=tinysrgb&w=600',
    imgAlt: 'Dog',
    title: 'Caring for Your Senior Cat',
    content:
      'As cats age, they require different types of care to maintain their health and wellbeing. Learn how to care for your senior cat to ensure they live a long and happy life.',
    link: 'https://www.vet.cornell.edu/departments-centers-and-institutes/cornell-feline-health-center/health-information/feline-health-topics/loving-care-older-cats'
  },
  {
    imgSrc:
      'https://images.pexels.com/photos/14215667/pexels-photo-14215667.jpeg?auto=compress&cs=tinysrgb&w=600',
    imgAlt: 'Cat',
    title: 'Signs Your Pet May Have Allergies',
    content:
      'Just like humans, pets can suffer from allergies. Learn about the signs that your pet may have allergies and what you can do to help them feel better.',
    link: 'https://acaai.org/allergies/allergic-conditions/pet-allergies/'
  },
  {
    imgSrc:
      'https://images.pexels.com/photos/4202917/pexels-photo-4202917.jpeg?auto=compress&cs=tinysrgb&w=600',
    imgAlt: 'Cat',
    title: 'Preventing and Treating Dental Disease in Cats',
    content:
      'Dental disease is a common problem in cats, but it can be prevented with proper care. Learn how to prevent and treat dental disease in your feline friend.',
    link: 'https://vcacanada.com/know-your-pet/dental-disease-in-cats'
  }
];

function PetHealthAndDiseaseInfo() {
  const classes = useStyles();
  const { setHeader } = useContext(HeaderContext);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'Pet Health and Diseases',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true,
      backRoute: '/pet-owner/home'
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div className={classes.root}>
      <main className={classes.main}>
        <section className="news">
          {ARTICLES.map((article, idx) => {
            return (
              <article key={idx} className={classes.article}>
                <div className={classes.articleContent}>
                  <img
                    src={article.imgSrc}
                    alt={article.imgAlt}
                    className={classes.image}
                  />
                  <div>
                    <h3>{article.title}</h3>
                    <p className={classes.articleContentDiv}>
                      {article.content}
                    </p>
                    <a className={classes.readMore} href={article.link}>
                      Read More
                    </a>
                  </div>
                </div>
              </article>
            );
          })}
        </section>
      </main>
    </div>
  );
}
export default PetHealthAndDiseaseInfo;
