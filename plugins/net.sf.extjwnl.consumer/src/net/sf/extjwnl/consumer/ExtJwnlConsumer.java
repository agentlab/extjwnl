/**
 *
 */
package net.sf.extjwnl.consumer;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.dictionary.Dictionary;
import net.sf.extjwnl.service.IExtjwnlService;
import net.sf.extjwnl.utilities.Examples;

/**
 * @author admin
 *
 */
public class ExtJwnlConsumer {

	private IExtjwnlService extjwnlService;
	private Dictionary dictionary;

	public synchronized void setDictionary(IExtjwnlService service) {
		System.err.println("Service was set. Thank you DS!");

		this.extjwnlService = service;
		this.dictionary = this.extjwnlService.getDictionary();

		if (null != dictionary) {
			try {
				new Examples(dictionary).go();
			}
			catch (JWNLException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// I know I should not use the service here but just for demonstration
//		System.out.println(service.getQuote());
	}

	// Method will be used by DS to unset the quote service
	public synchronized void unsetDictionary(IExtjwnlService service) {
		if (this.dictionary != null) {
			this.dictionary = null;
		}
		if (this.extjwnlService == service) {
			this.extjwnlService = null;
		}
	}

}
