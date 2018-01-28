# Login Demo

## Highlight
The purpose of the project is to demonstrate the approach of testing the UI in Android app,
by using espresso. The project has a very simple login screen, and based on the input it has to show
an error, or open the main application screen.
   
## Details
The key point of this project is to demonstrate the approach for getting a rock-solid UI tests that would
run on any environment, without any external dependencies. Traditionally, there were different approaches
for mocking a rest service that would run on the same machine with the emulator, so the app would make
the real calls. In this example, the test doubles are created and kept in the source code, so the app would not
need any external dependencies for running the UI tests. Furthermore, the replies are very fast so there
is no need for any idling resources. The idea is to focus on the UI, because the initial intention is to test the UI,
not the actual calls. <br/>
At the beginning, it's very important to note that the project has 2 flavors: **mock** and **prod**.
The reason behind is to separate the data source. The production data source implementation would make the real
work (calls to sever, etc), while the mock implementation would return mocked replies (aka test-doubles) based on the input.
This could have been done in many different ways (using dagger for instance), but the intention here is to
achieve the goal with as little dependencies as possible, so it will be very simple to be understood. 

The project consists of 2 branches:

### master
A very naive implementation of the login. The reason behind is to make the example as simple as possible
so the intention of the approach will be very clear and precise. Once again, the key point is to make the
UI tests simple and solid.
 
### full
This branch has the implementation very similar to the one in [master](#master). The difference is that it
also uses the architecture components (ViewModel and LiveData in particular), so the whole picture would be
nicer, and a little bit more real.

## Having Different/Better Ideas
If you know a better or nicer or simpler way for doing this, feel free to open a PR, or at least an issue
so we could discuss, share knowledge and learn.