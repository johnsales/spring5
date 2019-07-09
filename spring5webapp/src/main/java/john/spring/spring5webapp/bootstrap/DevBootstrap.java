package john.spring.spring5webapp.bootstrap;

import john.spring.spring5webapp.model.Author;
import john.spring.spring5webapp.model.Book;
import john.spring.spring5webapp.model.Publisher;
import john.spring.spring5webapp.repositories.AuthorRepository;
import john.spring.spring5webapp.repositories.BookRepository;
import john.spring.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData(){
        //Eric
        Author eric = new Author("Eric","Evans");
        Publisher p = new Publisher("Publisher 1","Address Publisher 1");
        Book ddd = new Book("Domain Drive Design", "1234", p);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(p);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod","Johnson");
        Publisher p2 = new Publisher("Publisher 2","Address Publisher 2");
        Book noEJB = new Book("J2EE Development without EJB","23444",p2);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        publisherRepository.save(p2);
        bookRepository.save(noEJB);
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
