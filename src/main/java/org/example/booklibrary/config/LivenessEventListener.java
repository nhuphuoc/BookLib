package org.example.booklibrary.config;

import org.example.booklibrary.service.Impl.BookServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LivenessEventListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(LivenessEventListener.class);
  @EventListener
  public void onEvent(AvailabilityChangeEvent<LivenessState> event) {
    switch (event.getState()) {
      case BROKEN:
        /*
          - notify others only when service meet severe errors
          - problems like db disconnection or redis disconnection will not be handled here
         */
        LOGGER.error("APPLICATION HAS BEEN BROKEN");
        break;
      case CORRECT:
        // we're back
        LOGGER.info("Service comback");
    }
  }
}
